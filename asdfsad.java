class Solution {
  public int equi ( int[] A ) {
    int result = -1;
    for(int i = 0; i  A.length; i++) {
        if (lowerBound(A,i) == upperBound(A,i)) {
            result = i;
            break;
        }
    }   
    
    return result;
  }
  public int lowerBound(int[] A,int index) {
      int sum = 0;
      for(int i = 0; i  index; i++) {
          sum += A[i];
      }
      
      return sum;
  }
  
  public int upperBound(int[] A,int index) {
      int sum = 0;
      for(int i = index+1; i  A.length; i++) {
          sum += A[i];
      }
      
      return sum;
  }
}