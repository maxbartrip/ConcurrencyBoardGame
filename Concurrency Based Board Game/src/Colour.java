
public enum Colour {
  BLACK,
  WHITE;
  
  private Colour opposite;
  
  static {
    BLACK.opposite = WHITE;
    WHITE.opposite = BLACK;
  }
  
  public Colour getOpposite() {
    return opposite;
  }
}
