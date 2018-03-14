package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_THEME;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ChangeThemeRequestEvent;

/**
 * Changes the theme of the UI.
 */
public class ChangeThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_SUCCESS = "Theme changed successfully";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the colour scheme of the application."
            + "Parameters: "
            + PREFIX_THEME + "THEME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_THEME + "light ";

    private final String[] themeStrings = {"view/LightTheme.css", "view/DarkTheme.css"};
    private final String newTheme;

    public ChangeThemeCommand(Integer themeIndex) {
        newTheme = themeStrings[themeIndex];
    }

    @Override
    public CommandResult execute() {
        EventsCenter.getInstance().post(new ChangeThemeRequestEvent(newTheme));
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ChangeThemeCommand // instanceof handles nulls
                && newTheme.equals(((ChangeThemeCommand) other).newTheme));
    }
}
