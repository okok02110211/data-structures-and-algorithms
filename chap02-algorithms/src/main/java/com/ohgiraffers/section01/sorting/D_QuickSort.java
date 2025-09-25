package com.ohgiraffers.section01.sorting;

import java.util.Arrays;

/* 퀵 정렬
 * 기준 값(pivot)을 선정하여 해당 값을 기준으로 작은 값과 큰 값으로 분할하는 방식이다.
 * 분할 후 각각의 부분 배열을 재귀적으로 정렬하여 정렬을 완성한다.
 * 평균적으로 O(nlogn)의 시간 복잡도를 가지며 일반적으로 성능이 좋다.
 * 하지만 피벗이 최악으로 선택 된 경우 O(n^2) 의 성능을 가질 수 있다. */
public class D_QuickSort {
    /* n 개의 정수가 주어질 때, 퀵 정렬 알고리즘을 사용하여 오름차순으로 정렬하는 프로그램 작성 */
    public static void solution(int[] arr) {
        System.out.println("원본 배열 : " + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("정렬 된 배열 : " + Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int low, int high) {
        if(low >= high) return; // 기본 종료 조건

        int boundary = partition(arr, low, high);   // 경계 인덱스 반환
        quickSort(arr, low, boundary - 1);
        quickSort(arr, boundary, high);
    }

    /* pivot 값 기준으로 [low...high] 를 두 구간으로 분할하고
     * 왼쪽 포인터가 멈춘 경계 인덱스를 반환한다. */
    private static int partition(int[] arr, int low, int high) {
        int i = low;
        int j =  high;
        int pivot = arr[low + (high - low) / 2];

        System.out.println("피벗 : " + pivot + " 기준, 인덱스 범위 : " + low + " ~ " + high);
        System.out.println("분할 전 배열 : " + Arrays.toString(arr));

        while(i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;

            if(i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        System.out.println("분할 후 배열 : " + Arrays.toString(arr));
        System.out.println("반환할 경계 인덱스 (오른쪽 구간 시작) : " + i);
        System.out.println("-----------------------------------------");
        return i;
    }

    private static void swap(int[] arr, int a, int b) {
        if (a == b) return;
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}