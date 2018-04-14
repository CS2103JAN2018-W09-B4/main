package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.card.Card;
import seedu.address.model.card.Schedule;
import seedu.address.model.card.UniqueCardList;
import seedu.address.model.card.exceptions.CardNotFoundException;
import seedu.address.model.card.exceptions.DuplicateCardException;
import seedu.address.model.cardtag.CardTag;
import seedu.address.model.cardtag.DuplicateEdgeException;
import seedu.address.model.tag.AddTagResult;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .equals comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {
    private static final Logger logger = LogsCenter.getLogger(AddressBook.class);

    private final UniqueTagList tags;
    private final UniqueCardList cards;
    private CardTag cardTag;

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tags = new UniqueTagList();
        cards = new UniqueCardList();
        cardTag = new CardTag();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Tags and Cards in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    public void setTags(List<Tag> tags) throws DuplicateTagException {
        this.tags.setTags(tags);
    }

    public void setCards(List<Card> cards) throws DuplicateCardException {
        this.cards.setCards(cards);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        List<Tag> syncedTagList = new ArrayList<>(newData.getTagList());

        try {
            setTags(syncedTagList);
        } catch (DuplicateTagException e) {
            throw new AssertionError("AddressBooks should not have duplicate tags");
        }

        List<Card> syncedCardList = new ArrayList<>(newData.getCardList());

        try {
            setCards(syncedCardList);
        } catch (DuplicateCardException e) {
            throw new AssertionError("AddressBooks should not have duplicate cards");
        }

        CardTag cardTag = new CardTag(newData.getCardTag());
        setCardTag(cardTag);
    }

    //// tag-level operations

    /**
     * Adds a tag to the card bank. Will not create a new Tag if it already exists. Returns the tag.
     *
     * @throws DuplicateTagException if an equivalent tag already exists.
     */
    public AddTagResult addTag(Tag p) {
        return tags.add(p);
    }

    /**
     * Replaces the given tag {@code target} in the list with {@code editedTag}.
     *
     * @throws DuplicateTagException if updating the tag's details causes the tag to be equivalent to
     *      another existing tag in the list.
     * @throws TagNotFoundException if {@code target} could not be found in the list.
     *
     */
    public void updateTag(Tag target, Tag editedTag)
            throws DuplicateTagException, TagNotFoundException {
        requireNonNull(editedTag);
        tags.setTag(target, editedTag);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * @throws TagNotFoundException if the {@code key} is not in this {@code AddressBook}.
     */
    public boolean removeTag(Tag key) throws TagNotFoundException {
        if (tags.remove(key)) {
            return true;
        } else {
            throw new TagNotFoundException(key);
        }
    }

    //// card-level operations

    /**
     * Adds a card to the address book.
     */
    public void addCard(Card c) throws DuplicateCardException {
        cards.add(c);
    }

    /**
     * Removes a card from the address book.
     * @param c card to remove
     * @throws CardNotFoundException
     */
    public void deleteCard(Card c) throws CardNotFoundException {
        cards.remove(c);
    }

    //// card-tag-level operations
    public void addEdge(Card c, Tag t) throws DuplicateEdgeException {
        cardTag.addEdge(c, t);
    }

    /**
     * Replaces the given card {@code target} in the list with {@code editedCard}.
     *
     * @throws DuplicateCardException if updating the card's details causes the card to be equivalent to
     *      another existing card in the list.
     * @throws CardNotFoundException if {@code target} could not be found in the list.
     *
     */
    public void updateCard(Card target, Card editedCard)
            throws DuplicateCardException, CardNotFoundException {
        requireNonNull(editedCard);
        cards.setCard(target, editedCard);
    }

    //@@author pukipuki
    /**
     * get cards due for review before {@code date}
     * @param date before this date
     * @return
     */
    public ObservableList<Card> getReviewList(LocalDateTime date) {
        requireNonNull(date);
        return getReviewList(date, cards.asObservableList());
    }

    /**
     * get cards due for review before {@code date} from a {@code cardsList}
     * @param date before this date
     * @param cardsList from this list
     * @return
     */
    public ObservableList<Card> getReviewList(LocalDateTime date, ObservableList<Card> cardsList) {
        requireAllNonNull(date, cardsList);

        FXCollections.sort(cardsList, Schedule.getByDate());
        logger.fine("Sorting filteredCards List.");

        cardsList = new FilteredList<Card>(cardsList, Schedule.before(date));
        logger.fine("Filtering filteredCards List.");

        ObservableList<Card> filteredList =  FXCollections.observableArrayList();
        for (Card each : cardsList) {
            filteredList.add(each);
        }

        return filteredList;
    }
    //@@author

    //// util methods
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(tags.asObservableList().size());
        stringBuilder.append(" Tags:\n");
        for (Tag tag : tags.asObservableList()) {
            stringBuilder.append(tag.toString() + "\n");
        }
        stringBuilder.append(cards.asObservableList().size());

        stringBuilder.append(" Cards:\n");
        for (Card card: cards.asObservableList()) {
            stringBuilder.append(card.toString() + "\n");
        }

        return stringBuilder.toString();
    }

    @Override
    public ObservableList<Tag> getTagList() {
        return tags.asObservableList();
    }

    @Override
    public ObservableList<Card> getCardList() {
        return cards.asObservableList();
    }

    @Override
    public CardTag getCardTag() {
        return cardTag;
    }

    public void setCardTag(CardTag cardTag) {
        this.cardTag = cardTag;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && this.tags.equals(((AddressBook) other).tags)
                && this.cards.equals(((AddressBook) other).cards))
                && this.cardTag.equals(((AddressBook) other).cardTag);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(tags, cards);
    }
}
