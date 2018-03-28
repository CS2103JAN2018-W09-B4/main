package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.BACK_DESC_CARD;
import static seedu.address.logic.commands.CommandTestUtil.BACK_DESC_MCQ_CARD;
import static seedu.address.logic.commands.CommandTestUtil.FRONT_DESC_CARD;
import static seedu.address.logic.commands.CommandTestUtil.FRONT_DESC_MCQ_CARD;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BACK_CARD;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FRONT_CARD;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MCQ_CARD_BACK;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MCQ_CARD_OPTION;
import static seedu.address.logic.commands.CommandTestUtil.OPTION_1_DESC_MCQ_CARD;
import static seedu.address.logic.commands.CommandTestUtil.OPTION_2_DESC_MCQ_CARD;
import static seedu.address.logic.commands.CommandTestUtil.OPTION_3_DESC_MCQ_CARD;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BACK_CARD_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRONT_CARD_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MCQ_BACK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MCQ_FRONT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MCQ_OPTION_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MCQ_OPTION_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MCQ_OPTION_3;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.AddCardCommand;
import seedu.address.model.card.Card;
import seedu.address.model.card.McqCard;
import seedu.address.testutil.CardBuilder;
import seedu.address.testutil.McqCardBuilder;

public class AddCardCommandParserTest {
    private AddCardCommandParser parser = new AddCardCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Card expectedCard = new CardBuilder().withFront(VALID_FRONT_CARD_1).withBack(VALID_BACK_CARD_1).build();
        McqCard expectedMcqCard = (McqCard) new McqCardBuilder().resetOptions()
                .addOption(VALID_MCQ_OPTION_1).addOption(VALID_MCQ_OPTION_2).addOption(VALID_MCQ_OPTION_3)
                .withFront(VALID_MCQ_FRONT)
                .withBack(VALID_MCQ_BACK).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + FRONT_DESC_CARD + BACK_DESC_CARD,
                new AddCardCommand(expectedCard));

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + FRONT_DESC_MCQ_CARD + BACK_DESC_MCQ_CARD
                + OPTION_1_DESC_MCQ_CARD + OPTION_2_DESC_MCQ_CARD + OPTION_3_DESC_MCQ_CARD,
                new AddCardCommand(expectedMcqCard));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCardCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_FRONT_CARD_1,
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_MCQ_BACK,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid front
        assertParseFailure(parser, INVALID_FRONT_CARD + BACK_DESC_CARD,
                Card.MESSAGE_CARD_CONSTRAINTS);

        // invalid back
        assertParseFailure(parser, FRONT_DESC_CARD + INVALID_BACK_CARD,
                Card.MESSAGE_CARD_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_FRONT_CARD_1 + BACK_DESC_CARD,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCardCommand.MESSAGE_USAGE));

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + FRONT_DESC_CARD + VALID_BACK_CARD_1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCardCommand.MESSAGE_USAGE));

        // invalid front for mcq cards
        assertParseFailure(parser, INVALID_FRONT_CARD + OPTION_1_DESC_MCQ_CARD + OPTION_2_DESC_MCQ_CARD
                + OPTION_3_DESC_MCQ_CARD + BACK_DESC_CARD, Card.MESSAGE_CARD_CONSTRAINTS);

        // invalid back for mcq cards
        assertParseFailure(parser, FRONT_DESC_CARD + OPTION_1_DESC_MCQ_CARD + OPTION_2_DESC_MCQ_CARD
                + OPTION_3_DESC_MCQ_CARD + INVALID_BACK_CARD, Card.MESSAGE_CARD_CONSTRAINTS);

        // invalid options for mcq cards
        assertParseFailure(parser, FRONT_DESC_MCQ_CARD + INVALID_MCQ_CARD_OPTION + OPTION_2_DESC_MCQ_CARD
                + OPTION_3_DESC_MCQ_CARD + BACK_DESC_MCQ_CARD, McqCard.MESSAGE_MCQ_CARD_CONSTRAINTS);

        // invalid back for mcq cards
        assertParseFailure(parser, FRONT_DESC_MCQ_CARD + OPTION_1_DESC_MCQ_CARD + OPTION_2_DESC_MCQ_CARD
                + OPTION_3_DESC_MCQ_CARD + INVALID_MCQ_CARD_BACK, McqCard.MESSAGE_MCQ_CARD_ANSWER_CONSTRAINTS);
    }
}
