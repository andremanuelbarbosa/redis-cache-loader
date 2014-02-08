package com.andremanuelbarbosa.redis.cache.loader;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Segment<T> {

    private final Long min;
    private final Long max;
    private final T value;

    public Segment(Long min, Long max, T value) {

        this.min = min;
        this.max = max;
        this.value = value;
    }

    public Long getMin() {

        return this.min;
    }

    public Long getMax() {

        return this.max;
    }

    public T getValue() {

        return this.value;
    }

    @Override
    public int hashCode() {

        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {

        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
