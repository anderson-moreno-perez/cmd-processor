package com.salesforce.tests.fs.entities;

import com.salesforce.tests.fs.exceptions.MaximumNameLengthException;
import com.salesforce.tests.fs.exceptions.RepeatedEntryNameException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.OffsetDateTime;
import java.util.Optional;

import static com.salesforce.tests.fs.utils.StringUtils.SLASH_CHAR;
import static java.time.ZoneOffset.UTC;
import static java.util.Objects.isNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@Getter
@EqualsAndHashCode
@ToString
public abstract class Entry {
    private static final int MAX_NAME_LENGTH = 100;

    String name;
    @Getter(NONE)
    Directory parent;
    @EqualsAndHashCode.Exclude
    final OffsetDateTime created;
    @EqualsAndHashCode.Exclude
    OffsetDateTime lastUpdated;

    protected Entry(@NonNull String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new MaximumNameLengthException(MAX_NAME_LENGTH);
        }

        this.name = name;
        this.created = OffsetDateTime.now(UTC);
        this.lastUpdated = OffsetDateTime.now(UTC);
    }

    protected Entry(@NonNull String name, @NonNull Directory parent) {
        this(name);
        this.parent = parent;
        addToParent();
    }

    public void changeName(@NonNull String name) {
        this.name = name;
        this.lastUpdated = OffsetDateTime.now(UTC);
    }

    public boolean changeParent(@NonNull Directory parent) {
        this.parent = parent;
        this.lastUpdated = OffsetDateTime.now(UTC);
        addToParent();
        return delete();
    }

    public boolean delete() {
        if (isNull(this.parent)) {
            return false;
        }

        return this.parent.deleteEntry(this);
    }

    public Optional<Directory> getParent() {
        return Optional.ofNullable(parent);
    }

    public String getFullPath() {
        var fullPath = new StringBuilder();
        createFullPathRecursively(this, fullPath);
        return fullPath.toString();
    }

    public boolean isDirectory() {
        return this instanceof Directory;
    }

    public abstract int size();

    private void addToParent() {
        if (!this.parent.addEntry(this)) {
            throw new RepeatedEntryNameException(this.name);
        }
    }

    private void createFullPathRecursively(Entry entry, StringBuilder fullPath) {
        entry.getParent().ifPresent(parentDirectory -> createFullPathRecursively(parentDirectory, fullPath));
        fullPath.append(SLASH_CHAR).append(entry.getName());
    }

}
