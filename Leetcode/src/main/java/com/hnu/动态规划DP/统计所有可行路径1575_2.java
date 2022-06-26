class 统计所有可行路径1575_2 {
    int mod = 1000000007;

    int [][] f=null;
    public int help(int[] locations, int start, int finish, int fuel) {

        if(fuel<Math.abs(locations[start]-locations[finish]))
        {
            f[start][fuel]=0;
            return 0;
        }

        if(f[start][fuel]!=-1) return f[start][fuel];

        int sum= start==finish?1:0;


        //当前城市为start时，下一个城市的所有可能是除了i意外 的，0 到 locations.length任意值
        for (int i = 0; i < locations.length ; i++) {
            if (i!=start) {
                int remain = fuel - (Math.abs(locations[start] - locations[i]));
                if (remain >= 0)//这里当下一个节点是i时，无法确定i之后是否有下一个节点。
                {
                    sum += help(locations, i, finish, remain);
                    sum = sum % mod;
                }

            }

        }
        f[start][fuel]=sum;
        return sum;




    }

    public int countRoutes(int[] locations, int start, int finish, int fuel) {

        if(fuel<Math.abs(locations[start]-locations[finish])) return 0;

        f=new int[locations.length][fuel+1];
        for (int i = 0; i < f.length ; i++) {
            for (int j = 0; j <fuel+1 ; j++) {
                f[i][j]=-1;

            }


        }



        return help(locations,start,finish,fuel);

    }
}