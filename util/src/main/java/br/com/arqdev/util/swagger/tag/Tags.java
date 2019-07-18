package br.com.arqdev.util.swagger.tag;

import br.com.arqdev.util.swagger.api.ApiListing;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import springfox.documentation.builders.BuilderDefaults;

public class Tags {
    private Tags() {
        throw new UnsupportedOperationException();
    }

    public static Set<Tag> toTags(Multimap<String, ApiListing> apiListings) {
        Iterable<ApiListing> allListings = Iterables.concat(BuilderDefaults.nullToEmptyMultimap(apiListings).asMap().values());
        List<Tag> tags = FluentIterable.from(allListings).transformAndConcat(collectTags()).toList();
        TreeSet<Tag> tagSet = Sets.newTreeSet(tagComparator());
        tagSet.addAll(tags);
        return tagSet;
    }

    public static Comparator<Tag> tagComparator() {
        return Ordering.from(byOrder()).compound(thenByName());
    }

    private static Comparator<Tag> thenByName() {
        return new Comparator<Tag>() {
            public int compare(Tag first, Tag second) {
                return first.getName().compareTo(second.getName());
            }
        };
    }

    private static Comparator<Tag> byOrder() {
        return new Comparator<Tag>() {
            public int compare(Tag first, Tag second) {
                return Integer.valueOf(first.getOrder()).compareTo(second.getOrder());
            }
        };
    }

    public static Function<String, Tag> toTag(final Function<String, String> descriptor) {
        return new Function<String, Tag>() {
            public Tag apply(String input) {
                return new Tag(input, (String)descriptor.apply(input));
            }
        };
    }

    public static Function<String, String> descriptor(final Map<String, Tag> tagLookup, final String defaultDescription) {
        return new Function<String, String>() {
            public String apply(String input) {
                return (String)Optional.fromNullable(tagLookup.get(input)).transform(Tags.toTagDescription()).or(defaultDescription);
            }
        };
    }

    private static Function<Tag, String> toTagDescription() {
        return new Function<Tag, String>() {
            public String apply(Tag input) {
                return input.getDescription();
            }
        };
    }

    public static Function<Tag, String> toTagName() {
        return new Function<Tag, String>() {
            public String apply(Tag input) {
                return input.getName();
            }
        };
    }

    static Function<ApiListing, Iterable<Tag>> collectTags() {
        return new Function<ApiListing, Iterable<Tag>>() {
            public Iterable<Tag> apply(ApiListing input) {
                return input.getTags();
            }
        };
    }

    public static Predicate<String> emptyTags() {
        return new Predicate<String>() {
            public boolean apply(String input) {
                return !Strings.isNullOrEmpty(input);
            }
        };
    }
}

