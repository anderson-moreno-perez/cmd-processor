package com.salesforce.tests.fs.entities;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableCollection;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class Directory extends Entry {
    private static final String ROOT = "root";

    Set<Entry> entries;

    private Directory(String name) {
        super(name);
        this.entries = new HashSet<>();
    }

    public Directory(@NonNull String name, @NonNull Directory parent) {
        super(name, parent);
        this.entries = new HashSet<>();
    }

    public int size() {
        return entries.stream().mapToInt(Entry::size).sum();
    }

    public int numberOfFiles() {
        int count = 0;

        for (Entry entry : entries) {
            if (entry.isDirectory()) {
                count += ((Directory) entry).numberOfFiles();
            }

            count++;
        }

        return count;
    }

    public boolean addEntry(@NonNull Entry entry) {
        return this.entries.add(entry);
    }

    public boolean deleteEntry(@NonNull Entry entry) {
        return this.entries.remove(entry);
    }

    public Collection<Entry> getContent() {
        return unmodifiableCollection(entries);
    }

    public static Directory createRoot() {
        return new Directory(ROOT);
    }

}
