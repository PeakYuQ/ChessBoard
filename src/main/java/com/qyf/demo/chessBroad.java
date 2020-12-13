package com.qyf.demo;


public class chessBroad{
    // 定义棋盘的大小：2^k，需要的骨牌数是：(4^k-1)/3
    public   int[][] board;
    public   int[][] active;

    public int[][] getActive() {
        return active;
    }

    public void setActive(int[][] active) {
        this.active = active;
    }

    // 定义一个全局变量，用来记录骨牌的编号
    private  int tile = 1;
    /**
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入特殊方格所在的行号：");
        int dr = scanner.nextInt();
        System.out.println("请输入特殊方格所在的列号：");
        int dc = scanner.nextInt();
        scanner.close();
        // 行号和列号与二位数组的下标相差 1
        chessBoard(0, 0, dr - 1, dc - 1, BOARD_SIZE);
        System.out.println("特殊方块在第 " + dr + " 行第 " + dc + "列，覆盖后的棋盘：");
        // 输出棋盘
        printBoard();
    }


     *
     * @param tr：棋盘左上角方格的行号
     * @param tc:棋盘左上角方格的列号
     * @param dr：特殊方格所在的行号
     * @param dc：特殊方格所在的列号
     * @param size：当前棋盘的大小
     */
    public void chessBoard(int tr, int tc, int dr, int dc, int size) {
        /* 1、当前棋盘的大小是 1 就返回 */
        if (size == 1) {
            return;
        }
        int t = tile++;
        // 分割棋盘，棋盘大小减半
        int s = size / 2;

        /* 2、覆盖左上角子棋盘 */
        if (dr < tr + s && dc < tc + s) {
            // 特殊方格在此棋盘中
            chessBoard(tr, tc, dr, dc, s);
        } else {
            // 特殊方格不在此棋盘中，用 t 号骨牌覆盖右下角
            board[tr + s - 1][tc + s - 1] = t;
            // 覆盖其余方格
            chessBoard(tr, tc, tr + s - 1, tc + s - 1, s);
        }

        /* 3、覆盖右上角子棋盘 */
        if (dr < tr + s && dc >= tc + s) {
            // 特殊方格在此棋盘中
            chessBoard(tr, tc + s, dr, dc, s);
        } else {
            // 特殊方格不在此棋盘中，用 t 号骨牌覆盖左下角
            board[tr + s - 1][tc + s] = t;
            // 覆盖其余方格
            chessBoard(tr, tc + s, tr + s - 1, tc + s, s);
        }

        /* 4、覆盖左下角子棋盘 */
        if (dr >= tr + s && dc < tc + s) {
            // 特殊方格在此棋盘中
            chessBoard(tr + s, tc, dr, dc, s);
        } else {
            // 特殊方格不在此棋盘中，用 t 号骨牌覆盖右上角
            board[tr + s][tc + s - 1] = t;
            // 覆盖其余方格
            chessBoard(tr + s, tc, tr + s, tc + s - 1, s);
        }

        /* 5、覆盖左下角子棋盘 */
        if (dr >= tr + s && dc >= tc + s) {
            // 特殊方格在此棋盘中
            chessBoard(tr + s, tc + s, dr, dc, s);
        } else {
            // 特殊方格不在此棋盘中，用 t 号骨牌覆盖右上角
            board[tr + s][tc + s] = t;
            // 覆盖其余方格
            chessBoard(tr + s, tc + s, tr + s, tc + s, s);
        }
    }


    public void search(int[][] board,int size){
        int i1 = (size * size - 1) / 3;
        for (int k = 1; k<=i1; k++ ) {
            int l = 0;

            for (int i = 0; i < size; i++) {
                if (l==6)
                    break;
                for (int j = 0; j < size; j++) {//遍历查找数组
                    if (board[i][j] == k) {
                        active[k][l] = i;
                        active[k][++l] = j;
                        ++l;
                    }
                    if (l == 6) {//判断砖
                        if ((active[k][0] == active[k][2]) && (active[k][1] == active[k][5]))
                            active[k][6] = 2;
                        else if ((active[k][0] == active[k][2]) && (active[k][3] == active[k][5]))
                            active[k][6] = 3;
                        else if ((active[k][2] == active[k][4]) && (active[k][1] == active[k][3]))
                            active[k][6] = 1;
                        else if ((active[k][2] == active[k][4]) && (active[k][1] == active[k][5]))
                            active[k][6] = 4;
                        break;
                    }
                }
            }
        }
    }


    public Integer searchColor(int[][] active,int color,int size) {
        int i1 = (size * size - 1) / 3;
        int num = 0;
        for (int k = 1; k <= i1; k++) {
            if (active[k][6] == color) {
                ++num;
            }
        }
        return num;
     }





    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

}
