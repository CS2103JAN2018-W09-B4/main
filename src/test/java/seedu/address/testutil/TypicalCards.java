package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_BACK_CARD_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BACK_CARD_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRONT_CARD_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRONT_CARD_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.card.Card;

/**
 * A utility class containing a list of {@code Tag} objects to be used in tests.
 */
public class TypicalCards {

    public static final Card MATHEMATICS_CARD = new CardBuilder()
            .withId("3648b549-d900-4f0e-8573-e3c9ab499054")
            .withFront("What is 1 + 1?")
            .withBack("2").build();
    public static final Card CHEMISTRY_CARD = new CardBuilder()
            .withId("f581860a-d5db-4925-aeab-1f1e441407f3")
            .withFront("Name two types of bonding between atoms")
            .withBack("Covalent and Ionic bonding").build();
    public static final Card COMSCI_CARD = new CardBuilder()
            .withId("3f92bd2c-affc-499c-a9a0-28b46fd61791")
            .withFront("What is Java?")
            .withBack("A programming language").build();
    public static final Card GEOGRAPHY_CARD = new CardBuilder()
            .withId("8c19a1f2-cfa2-4060-b9e3-67bd1ef101e0")
            .withFront("Which continent is Singapore in?")
            .withBack("Asia").build();
    public static final Card HISTORY_CARD = new CardBuilder()
            .withId("8c488abb-ab40-4a5b-9b1b-7ee78d453538")
            .withFront("When is Singapore's National Day?")
            .withBack("9th August 1965").build();
    public static final Card ECONOMICS_CARD = new CardBuilder()
            .withId("6375641d-822e-42b1-b98e-49e40b31c328")
            .withFront("What does GDP stand for?")
            .withBack("Gross Domestic Product").build();
    public static final Card LITERATURE_CARD = new CardBuilder()
            .withId("3dd96485-a4e4-42fe-a2fb-9e9f01d7f08d")
            .withFront("Who wrote To Kill A Mockingbird?")
            .withBack("Harper Lee").build();

    // Manually added
    public static final Card PHYSICS_CARD = new CardBuilder()
            .withId("7d59e0a2-4e64-4540-abdf-ce0fa552edb7")
            .withFront("Why do things fall?")
            .withBack("Gravity").build();
    public static final Card ENGLISH_CARD = new CardBuilder()
            .withId("8f4716c7-f82e-462f-bad1-e59f98b8624e")
            .withFront("What are action words?")
            .withBack("Verbs").build();

    // Manually added - Card's details found in {@code CommandTestUtil}
    public static final Card CS2103T_CARD = new CardBuilder().withFront(VALID_FRONT_CARD_1).withBack(VALID_BACK_CARD_1)
            .build();
    public static final Card CS2101_CARD = new CardBuilder().withFront(VALID_FRONT_CARD_2).withBack(VALID_BACK_CARD_2)
            .build();

    private TypicalCards() {} // prevents instantiation

    public static List<Card> getTypicalCards() {
        return new ArrayList<>(Arrays.asList(MATHEMATICS_CARD, CHEMISTRY_CARD, COMSCI_CARD, GEOGRAPHY_CARD,
                HISTORY_CARD, ECONOMICS_CARD, LITERATURE_CARD, PHYSICS_CARD, ENGLISH_CARD));
    }
}