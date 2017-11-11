package run;

import gov.nasa.worldwind.symbology.TacticalSymbol;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class SymbolConstructorThread implements Runnable{
    BlockingQueue<TacticalSymbol> symbolsInteralQueue;
    BlockingQueue<ArrayList<TacticalSymbol>> symbolsArrayInteralQueue;
    ArrayList<TacticalSymbol> symbolArrayList = new ArrayList<>();
    TacticalSymbol symbol;

    public SymbolConstructorThread(BlockingQueue<TacticalSymbol> symbolsQueue, BlockingQueue<ArrayList<TacticalSymbol>> symbolsArrayQueue){
        symbolsInteralQueue = symbolsQueue;
        symbolsArrayInteralQueue = symbolsArrayQueue;


    }

    @Override
    public void run() {
        while(true){
            try {
                while(symbolsInteralQueue.take() != null){
                    symbol = symbolsInteralQueue.take();
                    System.out.println("Received TACSY");
                    symbolArrayList.add(symbol);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                symbolsArrayInteralQueue.put(symbolArrayList);
                System.out.println("Sent Array");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
