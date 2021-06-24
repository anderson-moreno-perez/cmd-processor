package com.salesforce.tests.fs.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@Setter
@Getter
public final class File extends Entry {
    @NonNull
    String content;

    public File(@NonNull String name, @NonNull Directory parent) {
        super(name, parent);
    }

    public int size() {
        return this.content.length();
    }

}
