package com.ohgiraffers.section05.deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class Practice1 {

    static class Ballon{
        int order;
        int noteValue;
        Ballon(int order, int noteValue){
            this.order = order;
            this.noteValue = noteValue;
        }
    }

    public String solution(Integer[] notes){
        StringBuilder sb = new StringBuilder();

        Deque<Ballon> ballons = new ArrayDeque<>();
        for(int i = 0 ; i < notes.length; i++){
            ballons.addLast(new Ballon(i+1, notes[i]));
        }
        while(!ballons.isEmpty()){
            Ballon current = ballons.pollFirst();
            int noteValue = current.noteValue;
            sb.append(current.order);
            if (ballons.isEmpty()) {
                break;
            }
            sb.append(" ");

            if (noteValue > 0) {
                int steps = (noteValue - 1) % ballons.size();
                for (int i = 0; i < steps; i++) {
                    ballons.offerLast(ballons.pollFirst());
                }
            } else if (noteValue < 0) {
                int steps = Math.abs(noteValue) % ballons.size();
                for (int i = 0; i < steps; i++) {
                    ballons.offerFirst(ballons.pollLast());
                }
            }
        }

        return sb.toString().trim();
    }
}