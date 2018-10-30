package seedu.address.model.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.assignment.Mark;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ProfilePicture;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the person with. Each non-empty field value will replace the
 * corresponding field value of the person.
 */
public class EditPersonDescriptor {
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Map<String, Mark> marks;
    private Note note;

    public EditPersonDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditPersonDescriptor(EditPersonDescriptor toCopy) {
        setName(toCopy.name);
        setPhone(toCopy.phone);
        setEmail(toCopy.email);
        setAddress(toCopy.address);
        setTags(toCopy.tags);
        setMarks(toCopy.marks);
        setNote(toCopy.note);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public Person createEditedPerson(Person personToEdit) {
        assert personToEdit != null;

        Name updatedName = this.getName().orElse(personToEdit.getName());
        Phone updatedPhone = this.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = this.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = this.getAddress().orElse(personToEdit.getAddress());
        ProfilePicture updatedPicture = personToEdit.getProfilePicture();
        Set<Tag> updatedTags = this.getTags().orElse(personToEdit.getTags());
        Map<String, Mark> updatedMarks = this.getMarks().orElse(personToEdit.getMarks());
        Note updatedNote = this.getNote().orElse(personToEdit.getNote());

        return new Person(
            updatedName, updatedPhone, updatedEmail, updatedAddress, updatedPicture, updatedTags, updatedMarks,
            updatedNote);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Optional<Email> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    public void setMarks(Map<String, Mark> marks) {
        this.marks = (marks != null) ? new HashMap<>(marks) : null;
    }

    public Optional<Map<String, Mark>> getMarks() {
        return (marks != null) ? Optional.of(Collections.unmodifiableMap(marks)) : Optional.empty();
    }

    public Optional<Note> getNote() {
        return Optional.ofNullable(note);
    }

    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPersonDescriptor)) {
            return false;
        }

        // state check
        EditPersonDescriptor e = (EditPersonDescriptor) other;

        return getName().equals(e.getName())
            && getPhone().equals(e.getPhone())
            && getEmail().equals(e.getEmail())
            && getAddress().equals(e.getAddress())
            && getTags().equals(e.getTags())
            && getMarks().equals(e.getMarks())
            && getNote().equals(e.getNote());
    }
}