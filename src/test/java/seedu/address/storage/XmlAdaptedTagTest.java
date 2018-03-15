package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.storage.XmlAdaptedTag.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalTags.BIOLOGY;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Description;
import seedu.address.model.tag.Name;
import seedu.address.testutil.Assert;

public class XmlAdaptedTagTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ADDRESS = " ";

    private static final String VALID_NAME = BIOLOGY.getName().toString();
    private static final String VALID_ADDRESS = BIOLOGY.getDescription().toString();

    @Test
    public void toModelType_validTagDetails_returnsTag() throws Exception {
        XmlAdaptedTag tag = new XmlAdaptedTag(BIOLOGY);
        assertEquals(BIOLOGY, tag.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        XmlAdaptedTag tag =
                new XmlAdaptedTag(INVALID_NAME, VALID_ADDRESS);
        String expectedMessage = Name.MESSAGE_NAME_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        XmlAdaptedTag tag = new XmlAdaptedTag(null, VALID_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }



    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        XmlAdaptedTag tag =
                new XmlAdaptedTag(VALID_NAME, INVALID_ADDRESS);
        String expectedMessage = Description.MESSAGE_DESCRIPTION_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        XmlAdaptedTag tag = new XmlAdaptedTag(VALID_NAME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }
}
