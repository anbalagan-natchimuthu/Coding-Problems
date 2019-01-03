package interview.Matrix;

public class PrintSpiral {

	 private int n;
	    private int maxLength;
	    private String format;

	    public PrintSpiral(int n, String format) {
	        this.n = n;
	        maxLength = len(n * n);
	        this.format = format;
	    }

	    public void printSpiral() {
	        StringBuilder ans = new StringBuilder();
	        for (int i = 1; i <= n; i++) {
	            addNumber(ans, i);
	        }
	        ans.append('\n');
	        for (int i = 1; i <= n - 1; i++) {
	            int gap = Math.min(i - 1, n - 1 - i);
	            int start = (i <= n / 2) ? topLeft(i) : bottomLeft(n - i);
	            for (int j = 1; j <= n - 1; j++) {
	                if (j < (n - 1) / 2 + 1 && j <= gap) {
	                    addNumber(ans, topLeft(j) - (i - j));
	                } else if (j > (n - 1) / 2 + 1 && n - j <= gap) {
	                    addNumber(ans, topRight(n - j) + (i - (n - j)));
	                } else {
	                    addNumber(ans, start + (i <= n / 2 ? (j - i) : -(j - (n - i))));
	                }
	            }
	            addNumber(ans, n + i);
	            ans.append('\n');
	        }

	        System.out.print(ans);
	    }

	    private int topRight(int i) {
	        return topLeft(i) + (n - 2 * i);
	    }

	    private int bottomLeft(int i) {
	        return topLeft(i) + (2 * i - n);
	    }

	    private int topLeft(int i) {
	        return n * n - (n - 2 * i) * (n - 2 * i);
	    }

	    private int len(int i) {
	        int len = 0;
	        while (i > 0) {
	            len++;
	            i /= 10;
	        }
	        return len;
	    }

	    private void addNumber(StringBuilder ans, int number) {
	        if ("pretty".equals(format)) {
	            addSpacing(ans, number);
	        }
	        ans.append(number);
	        if ("csv".equals(format)) {
	            ans.append(',');
	        }
	    }

	    private void addSpacing(StringBuilder ans, int i) {
	        int length = len(i);
	        for (int j = 0; j <= maxLength - length; j++) {
	            ans.append(' ');
	        }
	    }

	    public static void main(String[] args) {
	        //new interview.Matrix.PrintSpiral(5, "csv").printSpiral();
	        System.out.println();
	        new PrintSpiral(5, "pretty").printSpiral();
	    }
	}
