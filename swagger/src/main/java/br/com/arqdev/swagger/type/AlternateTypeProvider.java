package br.com.arqdev.swagger.type;

import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import springfox.documentation.schema.AlternateTypeRule;

import java.util.List;

public class AlternateTypeProvider {
    private List<AlternateTypeRule> rules = Lists.newArrayList();

    public AlternateTypeProvider(List<AlternateTypeRule> alternateTypeRules) {
        this.rules.addAll(alternateTypeRules);
    }

    public ResolvedType alternateFor(ResolvedType type) {
        Optional<AlternateTypeRule> matchingRule = FluentIterable.from(this.rules).firstMatch(this.thatAppliesTo(type));
        return matchingRule.isPresent() ? ((AlternateTypeRule)matchingRule.get()).alternateFor(type) : type;
    }

    public void addRule(AlternateTypeRule rule) {
        this.rules.add(rule);
    }

    private Predicate<AlternateTypeRule> thatAppliesTo(final ResolvedType type) {
        return new Predicate<AlternateTypeRule>() {
            public boolean apply(AlternateTypeRule input) {
                return input.appliesTo(type);
            }
        };
    }
}

