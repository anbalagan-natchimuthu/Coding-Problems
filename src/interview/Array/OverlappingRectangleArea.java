package interview.Array;

public class OverlappingRectangleArea {

  public static void main(String[] args) {
    OverlappingRectangleArea area = new OverlappingRectangleArea();
    int[][] rectangles = new int[][]{{2, 1}, {5, 5}, {3, 2}, {5, 7}};
    System.out.println(area.findArea(rectangles));

    int[][] totalArea = new int[][]{{2, 1}, {5, 5}, {3, 2}, {5, 7}};
    System.out.println(area.findTotalArea(totalArea));

    totalArea = new int[][]{{2, 2}, {5, 7}, {3, 4}, {6, 9}};
    System.out.println(area.findTotalArea(totalArea));
  }

  // Problem : 1 Find Area of Overlapping Rectangles
  public int findArea(int[][] inputs) {

    // Rectangle 1 X and Y axis
    int x1b = Math.abs(inputs[0][0]);
    int y1b = Math.abs(inputs[0][1]);
    int x1t = Math.abs(inputs[1][0]);
    int y1t = Math.abs(inputs[1][1]);

    // Rectangle 2 X and Y axis
    int x2b = Math.abs(inputs[2][0]);
    int y2b = Math.abs(inputs[2][1]);
    int x2t = Math.abs(inputs[3][0]);
    int y2t = Math.abs(inputs[3][1]);

    // Rectangle Overlapping area
    int oxb = Math.max(x1b, x2b);
    int oxt = Math.min(x1t, x2t);
    int oyb = Math.max(y1b, y2b);
    int oyt = Math.min(y1t, y2t);

    System.out.println(oxb + "::" + oxt + "::" + oyb + "::" + oyt);

    // Check if Rectangles are overlapping
    if (oxt >= oxb) {
      return (oxt - oxb) * (oyt - oyb);
    } else {
      return 0;
    }
  }


  // Problem : 2 Find total Area of Two overlapping rectangles
  public int findTotalArea(int[][] inputs) {

    // Rectangle 1 X and Y axis
    int x1b = Math.abs(inputs[0][0]);
    int y1b = Math.abs(inputs[0][1]);
    int x1t = Math.abs(inputs[1][0]);
    int y1t = Math.abs(inputs[1][1]);

    // Rectangle 2 X and Y axis
    int x2b = Math.abs(inputs[2][0]);
    int y2b = Math.abs(inputs[2][1]);
    int x2t = Math.abs(inputs[3][0]);
    int y2t = Math.abs(inputs[3][1]);

    // Rectangle Overlapping area
    int oxb = Math.max(x1b, x2b);
    int oxt = Math.min(x1t, x2t);
    int oyb = Math.max(y1b, y2b);
    int oyt = Math.min(y1t, y2t);

    System.out.println(oxb + "::" + oxt + "::" + oyb + "::" + oyt);

    // Check if Rectangles are overlapping
    if (oxt >= oxb) {
      int rect1Area = Math.abs(x1t -x1b) * Math.abs(y1t - y1b);
      int rect2Area = Math.abs(x2t -x2b) * Math.abs(y2t - y2b);
      return rect1Area + rect2Area - (oxt - oxb) * (oyt - oyb);
    } else {
      return 0;
    }
  }
}
