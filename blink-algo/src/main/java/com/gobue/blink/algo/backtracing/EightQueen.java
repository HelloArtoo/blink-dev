package com.gobue.blink.algo.backtracing;

/**
 * 八皇后问题，解法
 */
public class EightQueen {

    /**
     * @param n 棋盘数量
     */
    public void findAllSolutions(int n) {
        int[] rst = new int[n];
        calcQueens(rst, 0, n);
    }

    /**
     * n皇后问题
     *
     * @param rst 结果数组
     * @param row 计算第 row 行
     * @param n   总共有多少个皇后
     */
    private void calcQueens(int[] rst, int row, int n) {

        if (row == n) {
            printMatrix(rst, n);
            return;
        } else {
            for (int column = 0; column < n; column++) {
                if (isOk(rst, row, column, n)) {
                    rst[row] = column;
                    calcQueens(rst, row + 1, n);
                }
            }
        }
    }

    private boolean isOk(int[] rst, int row, int column, int n) {
        int leftUp = column, rightUp = column;
        for (int i = row - 1; i >= 0; i--) {
            leftUp--;
            rightUp++;
            if (rst[i] == column) { //纵列上方有Queen
                return false;
            }

            if (leftUp >= 0 && rst[i] == leftUp) { // 左上有Queen
                return false;
            }

            if (rightUp < n && rst[i] == rightUp) { // 右上有Queen
                return false;
            }
        }

        return true;
    }

    private void printMatrix(int[] rst, int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(i == rst[j] ? "Q " : "* ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) {
        new EightQueen().findAllSolutions(8);
    }
}
