public class Board{

  private String[][] squares;

  public Board()
  {
    squares = new String[10][10];
    for (int r = 0; r < squares.length; r++)
    {
      for (int c = 0; c < squares[0].length; c++)
      {
        squares[r][c] = "-";
      }
    }
  }

  public String toString()
  {
    String gb = "";
    for (int r = 0; r<squares.length; r++)
    {
      for (int c = 0; c<squares[0].length; c++)
      {
        gb += squares[r][c] + " ";
      }
      gb += "\n";
    }
    return gb;
  }

  public boolean addShip(int row, int col, int len, boolean horizontal)
  {
    if (row > 9 || row < 0 || col > 9 || col < 0)
    {
      return false;
    }
    
    if (horizontal) //traverse through columns //row stays
    {
      if (col + len > squares.length) //check if ship will go off of board
        {
          return false;
        }
        
      for(int i = col; i<col+len; i++)
      {
        if (!squares[row][i].equals("-")) //check if other ships are in the way
        {
          return false;
        }
      }
      
      for(int i = col; i<col+len; i++) //iterate through to build the ship
      {
        squares[row][i] = "b";
      }
      return true;
    }
    
    if (!horizontal) //column stays
    {
      if (row + len > squares[0].length) //check if ship will go off of board
      {
        return false;
      }
      
      for (int i = row; i<row+len; i++) //check if other ships are in the way
      {
        if (!squares[i][col].equals("-"))
        {
          return false;
        }
      }
      
      for (int i = row; i<row+len; i++) //iterate through to build the ship
      {
        squares[i][col] = "b"; 
      }
       return true; 
    }
    return true;
  }

  public boolean foundShip(int len)
  {
    //horizontal check
    for (int r = 0; r<squares.length; r++)
    {
      int count = 0;
      while (count < squares[0].length)
      {
        int consec = 0;
        while (count < squares[0].length && squares[r][count].equals("b")) 
        {
          consec++;
          count++;
        }
        if (consec == len)
        {
          return true;
        }
        consec = 0;
        count++;
      }
    }
    
    //vertical check
    //do it column major
    for (int c = 0; c < squares[0].length; c++)
    {
      int count = 0;
      while (count < squares.length)
      {
        int consec = 0;
        while (count < squares.length && squares[count][c].equals("b"))
        {
          count++;
          consec++;
        }
        if (consec == len)
        {
          return true;
        }
        consec = 0;
        count++;
      }
    }
    
    return false;
  }

  public int shoot(int row, int col)
  {
    int rv = 0;
    if (row >= squares.length || row < 0 || col >= squares[0].length || col < 0)
    {
      return -1;
    }
    
    if (squares[row][col].equals("-"))
    {
      squares[row][col] = "m";
      return 0;
    }
    
    if (squares[row][col].equals("b"))
    {
      squares[row][col] = "x";
      return 1;
    }
    
    if (squares[row][col].equals("x") || squares[row][col].equals("m"))
    {
      return 2;
    }
    
    return rv;
  }

  public boolean gameOver()
  {
    for (int r = 0; r<squares.length; r++)
    {
      for (int c = 0; c<squares[0].length; c++)
      {
        if (squares[r][c].equals("b"))
        {
          return false;
        }
      }
    }
    return true;
  }


}
