package com.liuil.datastructure;

/**
 * 二分法查找
 */
public class BinarySearch {

    /**
     * 查找任何匹配的对象，如果不存在，返回-1
     *
     * @param input
     * @param data
     * @return
     */
    private static int findAny(int[] input, int data) {
        if (input == null) {
            throw new IllegalArgumentException("input is null");
        }
        int low = 0;
        int high = input.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (input[mid] < data) {
                low = mid + 1;
            } else if (input[mid] > data) {
                high = mid - 1;
            } else {
                //  只要相等就返回
                return mid;
            }
        }

        return -1;
    }


    /**
     * 寻找第一个相等的，如果不存在则返回-1
     */
    private static int findFirst(int[] input, int data) {
        if (input == null) {
            throw new IllegalArgumentException("input is null");
        }
        int low = 0;
        int high = input.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (input[mid] < data) {
                low = mid + 1;
            } else if (input[mid] > data) {
                high = mid - 1;
            } else {
                // 如果是第一个或者前一个不与查找的相同，则直接返回，否则再向前查找
                if (mid == 0 || input[mid - 1] != data) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 寻找最后一个相等的，如果不存在则返回-1
     */
    private static int findLast(int[] input, int data) {
        if (input == null) {
            throw new IllegalArgumentException("input is null");
        }
        int low = 0;
        int high = input.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (input[mid] < data) {
                low = mid + 1;
            } else if (input[mid] > data) {
                high = mid - 1;
            } else {
                // 如果是最后一个或者后一个不与查找的相同，则直接返回，否则再向后查找
                if (mid == input.length - 1 || input[mid + 1] != data) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //             0  1  2  3  4  5  6
        int[] input = {1, 2, 3, 3, 3, 5, 6};
        System.out.println("findAny result is " + findAny(input, 3));
        System.out.println("findFirst result is " + findFirst(input, 3));
        System.out.println("findLast result is " + findLast(input, 3));

    }

}
