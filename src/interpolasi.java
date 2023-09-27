public class interpolasi {
    public static double[][] interpolate(double[][] input){
        double[][] mat = new double[input.length][input.length+1];
        int row = mat.length;
        int col = mat[0].length;
        for (int i=0;i<row;i++){
            for (int j = 0;j<col-1;j++){
                mat[i][j] = Math.pow(input[i][0], j);
            }
            mat[i][col-1] = input[i][1];
        }
        double[][] result = new double[row][1];
        result = spl.gauss_Jordan_Spl(mat);
        return result;
    }
}
