package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.person.Note.MESSAGE_NOTE_CONSTRAINTS;
import static seedu.address.model.person.Note.NOTE_INVALIDATION_REGEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.common.EditPersonDescriptor;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;

/**
 * Adds a note about the specified student if there is none, adds to end with whitespace-prefix if it already exists.
 */
public class NoteCommand extends Command {

    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Adds a note about the student specified by the given index. "
        + "Parameters: INDEX (must be a positive integer) "
        + "TEXT\n"
        + "Example: " + COMMAND_WORD + " 1" + " hardworking student";

    public static final String MESSAGE_SUCCESS = "New note added to %1$s";

    /**
     * Guaranteed to be a positive integer, {@code NoteCommandParser}.
     */
    private final Index studentIndex;
    private String textToAdd;

    /**
     * Creates a NoteCommand to add the specified {@code text} as a note about the student.
     */
    public NoteCommand(Index index, String text) {
        requireAllNonNull(index, text);
        studentIndex = index;
        textToAdd = text;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // Checks if index is valid, not more than list size
        int zeroBasedIndex = studentIndex.getZeroBased();
        if (zeroBasedIndex >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        // Checks if text is valid
        if (textToAdd.matches(NOTE_INVALIDATION_REGEX)) {
            throw new CommandException(MESSAGE_NOTE_CONSTRAINTS);
        }

        Person studentToReplace = lastShownList.get(zeroBasedIndex);
        EditPersonDescriptor descriptor = new EditPersonDescriptor();

        // add whitespace in front of text if appending to existing text
        if (studentToReplace.hasNote()) {
            textToAdd = " " + textToAdd;
        }

        Note note = studentToReplace.getNote();
        Note updatedNote = note.add(textToAdd);
        descriptor.setNote(updatedNote);
        Person newStudent = descriptor.createEditedPerson(studentToReplace);
        model.updatePerson(studentToReplace, newStudent);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, newStudent));
    }

    /**
     * Checks if same object or fields are the same.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoteCommand // instanceof handles nulls
                && studentIndex.equals(((NoteCommand) other).studentIndex)
                && textToAdd.equals(((NoteCommand) other).textToAdd));
    }
}