package com.popov.test_tasks_challenge.coding_challenge_21072022;

/**
 * Implement the function Segment.areas() that when
 * given a circle's radium r and angle of a segment in degrees
 * a return both the area inside the segment,
 * and the area of the circle that's outside the segment;
 *
 * CircleArea = pi*r^2
 * SegmentArea = r^2/2*(a*pi/180 - sin(a*pi/180))
 * Outside the segment = CircleArea - SegmentArea
 */
public class Segment {
    public static Areas areas(double r, double a) {
        double circleArea = Math.PI*Math.pow(r, 2);
        double segmentArea = (Math.pow(r, 2)/2)
                *(a*(Math.PI/180)-Math.sin(a*(Math.PI/180)));
        return new Areas(segmentArea, circleArea-segmentArea);
    }

    public static class Areas {
        public final double inside, outside;
        public Areas(double inside, double outside) {
            this.inside = inside;
            this.outside = outside;
        }
    }

    public static void main(String[] args) {
        Areas areas = Segment.areas(10, 90);
        System.out.println("Areas: " + areas.inside + ", " + areas.outside);
    }
}