public class interpolasi {
    public static double[][] interpol(double[][] mat){
        int row = mat.length;
        int col = mat[0].length;
        double[][] interpol_mat = operator.copyMatrix(mat);
        double[][] result = new double[row][1];
        for (int i=0;i<row;i++){
            for (int j=0;j<col-1;j++){
                interpol_mat[i][j] = Math.pow(interpol_mat[i][col-1],(j+1));
            }
        }
        result = spl.gauss_Jordan_Spl(interpol_mat);
        return result;
    }
    
}
