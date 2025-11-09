
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    
    public static void main( String[] args ) throws ExecutionException, InterruptedException {
        new Main().learnFutures();
        
//        Runnable r = () -> {
//            for(int i=0;i<50;i++){
//                System.out.println(Thread.currentThread().getName()+ " "+i);
//            }
//        };
//
//        Thread th = new Thread(r, "Thread A");
//        th.start();
//        Thread th2 = new Thread(r, "Thread B");
//        th2.start();
//        r.run();
//
        
        System.out.println( "Exit" );
    }
    
    public void learnFutures() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool( 2 );
        es.execute( ()->System.out.println("starting in main") );
        
        var futures = new ArrayList<Future<String>>();
        
        for( int i=0;i<3;i++){
            var ind = i;
            futures.add( es.submit( () -> {
                Thread.sleep( 1000 );
                return ("finished job id "+ ind); } ) );
        }
        es.shutdown();
        
        for (var f: futures){
            System.out.println(" > "+ f.get());
        }
    }
}