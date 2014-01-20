package com.andremanuelbarbosa.redis.cacheloader;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SegmentTree<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SegmentTree.class);

    private Node node;

    public SegmentTree(final List<Segment<T>> segments) {

        this.init(segments);
    }

    private List<Long> getCoordinates(final List<Segment<T>> segments) {

        final long currentTimeMillis = System.currentTimeMillis();

        final List<Long> coordinates = new LinkedList<Long>();

        for (Segment<T> segment : segments) {

            coordinates.add(segment.getMin());
            coordinates.add(segment.getMax());
        }

        Collections.sort(coordinates);

        LOGGER.info("Execution Time: {}ms", (System.currentTimeMillis() - currentTimeMillis));

        LOGGER.debug("Coordinates: {}", coordinates.toString());

        return coordinates;
    }

    private List<Interval> getAtomicIntervals(final List<Segment<T>> segments) {

        final List<Long> coordinates = this.getCoordinates(segments);

        final long currentTimeMillis = System.currentTimeMillis();

        final List<Interval> atomicIntervals = new LinkedList<Interval>();

        if (coordinates.get(0) > Long.MIN_VALUE) {

            atomicIntervals.add(new Interval(Long.MIN_VALUE, coordinates.get(0) - 1));
        }

        for (int i = 0; i < (coordinates.size() - 1); i++) {

            Interval atomicInterval = new Interval(coordinates.get(i), coordinates.get(i + 1));

            if (!atomicIntervals.contains(atomicInterval)) {

                atomicIntervals.add(atomicInterval);
            }
        }

        if (coordinates.get(coordinates.size() - 1) < Long.MAX_VALUE) {

            atomicIntervals.add(new Interval(coordinates.get(coordinates.size() - 1) + 1,
                    Long.MAX_VALUE));
        }

        LOGGER.info("Execution Time: {}ms", (System.currentTimeMillis() - currentTimeMillis));

        LOGGER.debug("Atomic Intervals: {}", atomicIntervals.toString());

        return atomicIntervals;
    }

    private void init(final List<Segment<T>> segments) {

        if (segments == null || segments.size() == 0) {

            return;
        }

        final List<Interval> atomicIntervals = this.getAtomicIntervals(segments);

        for (Interval atomicInterval : atomicIntervals) {

        }
    }

    private int getSize(final Node node) {

        int size = 1;

        if (node.getLeftChild() != null) {

            size += this.getSize(node.getLeftChild());
        }

        if (node.getRightChild() != null) {

            size += this.getSize(node.getRightChild());
        }

        return size;
    }

    public int getSize() {

        if (this.node == null) {

            return 0;
        }

        return this.getSize(this.node);
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    class Node {

        private Long min;
        private Long max;
        private List<T> values;

        private Node leftChild;
        private Node rightChild;

        public Node(Long min, Long max) {

            this.min = min;
            this.max = max;

            this.values = new LinkedList<T>();
        }

        public Long getMin() {

            return this.min;
        }

        public void setMin(Long min) {

            this.min = min;
        }

        public Long getMax() {

            return this.max;
        }

        public void setMax(Long max) {

            this.max = max;
        }

        public List<T> getValues() {

            return this.values;
        }

        public void addValue(T value) {

            this.values.add(value);
        }

        public void setValues(List<T> values) {

            this.values = values;
        }

        public Node getLeftChild() {

            return this.leftChild;
        }

        public void setLeftChild(Node leftChild) {

            this.leftChild = leftChild;
        }

        public Node getRightChild() {

            return this.rightChild;
        }

        public void setRightChild(Node rightChild) {

            this.rightChild = rightChild;
        }

        @Override
        public String toString() {

            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }
}
