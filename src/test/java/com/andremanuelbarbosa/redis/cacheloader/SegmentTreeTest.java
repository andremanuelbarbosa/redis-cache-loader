package com.andremanuelbarbosa.redis.cacheloader;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.andremanuelbarbosa.redis.cache.loader.Segment;
import com.andremanuelbarbosa.redis.cache.loader.SegmentTree;

@Ignore
public class SegmentTreeTest {

    @Test
    public void shouldReturnSizeZeroWhenSegmentsIsNull() {

        SegmentTree<String> segmentTree = new SegmentTree<String>(null);

        Assert.assertEquals(0, segmentTree.getSize());
    }

    @Test
    public void shouldReturnSizeOneWhenOneSegmentIsAdded() {

        List<Segment<String>> segments = new LinkedList<Segment<String>>();
        segments.add(new Segment<String>(1l, 2l, "A"));

        SegmentTree<String> segmentTree = new SegmentTree<String>(segments);

        Assert.assertEquals(1, segmentTree.getSize());
    }

    @Test
    public void shouldReturnSizeTwoWhenTwoSpacedSegmentsAreAdded() {

        List<Segment<String>> segments = new LinkedList<Segment<String>>();
        segments.add(new Segment<String>(1l, 2l, "A"));
        segments.add(new Segment<String>(4l, 5l, "B"));

        SegmentTree<String> segmentTree = new SegmentTree<String>(segments);

        Assert.assertEquals(2, segmentTree.getSize());
    }

    @Test
    public void shouldReturnSizeTwoWhenTwoConsecutiveSegmentsAreAdded() {

        List<Segment<String>> segments = new LinkedList<Segment<String>>();
        segments.add(new Segment<String>(1l, 2l, "A"));
        segments.add(new Segment<String>(2l, 3l, "B"));

        SegmentTree<String> segmentTree = new SegmentTree<String>(segments);

        Assert.assertEquals(2, segmentTree.getSize());
    }

    @Test
    public void shouldReturnSizeTwoWhenTwoOverlappingSegmentsAreAdded() {

        List<Segment<String>> segments = new LinkedList<Segment<String>>();
        segments.add(new Segment<String>(1l, 3l, "A"));
        segments.add(new Segment<String>(2l, 4l, "B"));

        SegmentTree<String> segmentTree = new SegmentTree<String>(segments);

        Assert.assertEquals(2, segmentTree.getSize());
    }
}
