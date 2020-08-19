package com.gobue.blink.algo.array;

import java.util.*;

/**
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 * <p>
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 * <p>
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
 * <p>
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/matrix-cells-in-distance-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AllCellDistOrder {

    /**
     * 排序的方式
     *
     * @param R
     * @param C
     * @param r0
     * @param c0
     * @return
     */
    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {
        List<int[]> lst = new ArrayList<>(R * C);
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                lst.add(new int[]{i, j});
            }
        }

        Collections.sort(lst, (o1, o2) -> {
            int dis1 = Math.abs(o1[0] - r0) + Math.abs(o1[1] - c0);
            int dis2 = Math.abs(o2[0] - r0) + Math.abs(o2[1] - c0);
            return dis1 - dis2;
        });

        int[][] ans = new int[R * C][2];
        for (int i = 0; i < lst.size(); i++) {
            ans[i] = lst.get(i);
        }
        return ans;
    }

    public static int[][] allCellsDistOrder3(int R, int C, int r0, int c0) {
        Set<int[]> set = new TreeSet<>((o1, o2) -> {
            int dis1 = Math.abs(o1[0] - r0) + Math.abs(o1[1] - c0);
            int dis2 = Math.abs(o2[0] - r0) + Math.abs(o2[1] - c0);
            return dis1 - dis2;
        });
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                set.add(new int[]{i, j});
            }
        }
        int[][] ans = new int[R * C][2];
        int index = 0;
        for (int[] item : set) {
            ans[index++] = item;
        }
        return ans;
    }

    /**
     * BFS优先遍历的方式，曼哈顿距离遍历
     *
     * @param R
     * @param C
     * @param r0
     * @param c0
     * @return
     */
    public static int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        //BFS
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r0, c0});
        boolean[][] visted = new boolean[R][C];
        visted[r0][c0] = true;

        int[][] rst = new int[R * C][2];
        int rstIndex = 0;
        while (!queue.isEmpty()) {
            List<int[]> lst = new ArrayList<>();
            while (!queue.isEmpty()) {
                int[] val = queue.poll();
                rst[rstIndex++] = val;
                lst.add(val);
            }

            for (int[] node : lst) {
                int row = node[0], col = node[1];
                //上
                if (row > 0 && !visted[row - 1][col]) {
                    queue.offer(new int[]{row - 1, col});
                    visted[row - 1][col] = true;
                }
                //下
                if (row + 1 < R && !visted[row + 1][col]) {
                    queue.offer(new int[]{row + 1, col});
                    visted[row + 1][col] = true;
                }
                //左
                if (col > 0 && !visted[row][col - 1]) {
                    queue.offer(new int[]{row, col - 1});
                    visted[row][col - 1] = true;
                }
                //右
                if (col + 1 < C && !visted[row][col + 1]) {
                    queue.offer(new int[]{row, col + 1});
                    visted[row][col + 1] = true;
                }
            }
        }
        return rst;
    }


    public static void main(String[] args) {
        int[][] ints = allCellsDistOrder3(2, 2, 0, 1);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(Arrays.toString(ints[i]));
        }

    }
}
