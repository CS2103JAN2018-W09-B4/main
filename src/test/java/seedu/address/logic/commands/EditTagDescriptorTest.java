package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_COMSCI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_COMSCI;

import org.junit.Test;

import seedu.address.testutil.EditTagDescriptorBuilder;

public class EditTagDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditTagDescriptor descriptorWithSameValues = new EditCommand.EditTagDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditCommand.EditTagDescriptor editedAmy =
                new EditTagDescriptorBuilder(DESC_AMY).withName(VALID_NAME_COMSCI).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditTagDescriptorBuilder(DESC_AMY).withDescription(VALID_DESCRIPTION_COMSCI).build();
        assertFalse(DESC_AMY.equals(editedAmy));

    }
}
