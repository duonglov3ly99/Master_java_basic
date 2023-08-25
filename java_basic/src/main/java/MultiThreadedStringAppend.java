public class MultiThreadedStringAppend {
        public static void main(String[] args) {
            // Tạo một StringBuilder rỗng
            long start1 = System.currentTimeMillis();
            StringBuilder stringBuilder = new StringBuilder("");

            // Số lần thêm chuỗi "abc"
            int iterations = 25000;

            // Số luồng bạn muốn chạy cùng lúc
            int numThreads = 10;

            // Tạo và bắt đầu các luồng
            Thread[] threads = new Thread[numThreads];
            for (int i = 0; i < numThreads; i++) {
                threads[i] = new Thread(new StringBuilderTask(stringBuilder, iterations));
                threads[i].start();
            }

            // Chờ cho đến khi tất cả các luồng hoàn thành
            for (int i = 0; i < numThreads; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long end1 = System.currentTimeMillis()-start1;
            // In độ dài của StringBuilder (kết quả cuối cùng)
            System.out.println("Kết quả cuối cùng: Độ dài của chuỗi vs String builder= " + stringBuilder.length()+ "  time: "+end1);
            long start2 = System.currentTimeMillis();
            StringBuffer stringBuffer = new StringBuffer("");

            // Tổng số lần thêm chuỗi "abc" của tất cả các luồng
            int totalIterations = 250000;

            // Số luồng bạn muốn chạy cùng lúc
            int numThreads1 = 10;

            // Tạo và bắt đầu các luồng
            Thread[] threads1 = new Thread[numThreads];
            int iterationsPerThread = totalIterations / numThreads; // Số lần cho mỗi luồng
            for (int i = 0; i < numThreads; i++) {
                threads[i] = new Thread(new StringBufferTask(stringBuffer, iterationsPerThread));
                threads[i].start();
            }

            // Chờ cho đến khi tất cả các luồng hoàn thành
            for (int i = 0; i < numThreads; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long end2 = System.currentTimeMillis()-start2;
            // In độ dài của StringBuffer (kết quả cuối cùng)
            System.out.println("Kết quả cuối cùng: Độ dài của chuỗi String buffer= " + stringBuffer.length()+ "  time: "+end2);


        }

        // Luồng thêm chuỗi "abc" vào StringBuilder
        static class StringBuilderTask implements Runnable {
            private StringBuilder stringBuilder;
            private int iterations;

            public StringBuilderTask(StringBuilder stringBuilder, int iterations) {
                this.stringBuilder = stringBuilder;
                this.iterations = iterations;
            }

            @Override
            public void run() {
                for (int i = 0; i < iterations; i++) {
                    stringBuilder.append("abc");
                }
            }
        }
        static class StringBufferTask implements Runnable {
            private StringBuffer stringBuffer;
            private int iterations;

            public StringBufferTask(StringBuffer stringBuffer, int iterations) {
                this.stringBuffer = stringBuffer;
                this.iterations = iterations;
            }

            @Override
            public void run() {
                for (int i = 0; i < iterations; i++) {
                    stringBuffer.append("abc");
                }
            }
        }

}

