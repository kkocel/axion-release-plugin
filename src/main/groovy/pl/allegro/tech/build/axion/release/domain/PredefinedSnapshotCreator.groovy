package pl.allegro.tech.build.axion.release.domain

import pl.allegro.tech.build.axion.release.domain.scm.ScmPosition

enum PredefinedSnapshotCreator {

    SIMPLE('simple', { String version, ScmPosition position ->
        return "-SNAPSHOT";
    }),

    private final String type

    final Closure snapshotCreator

    private PredefinedSnapshotCreator(String type, Closure c) {
        this.type = type
        this.snapshotCreator = c
    }

    static Closure snapshotCreatorFor(String type) {
        PredefinedSnapshotCreator creator = values().find { it.type == type }
        if (creator == null) {
            throw new IllegalArgumentException("There is no predefined snapshot creator with $type type. " +
                    "You can choose from: ${values().collect { it.type }}");
        }
        return creator.snapshotCreator
    }
}
