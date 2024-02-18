package src;

public class World {

    static Room[][] aWorld;
    private int mapSize = 20;

    public World(final Room[][] pWorld) {
        aWorld = pWorld;

    }

    public Room getZoneExit(final Room pRoom, final String pDirection) {
        int a = getIndex(pRoom)[0];
        int b = getIndex(pRoom)[1];
        int x = 0;
        int y = 0;
        if (pDirection.equals("North")) {
            x = -1;
        } else if (pDirection.equals("South")) {
            x = 1;
        } else if (pDirection.equals("East")) {
            y = 1;
        } else if (pDirection.equals("West")) {
            y = -1;
        }
        return aWorld[modulo((a + x), mapSize)][modulo((b + y), mapSize)];
    }

    public Room getZone(final Room pRoom) {
        int a = getIndex(pRoom)[0];
        int b = getIndex(pRoom)[1];

        return aWorld[a][b];
    }

    private int[] getIndex(final Room pRoom) {
        int[] tabIndex = new int[2];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++)

                if (pRoom.equals(aWorld[i][j])) {
                    tabIndex[0] = i;
                    tabIndex[1] = j;
                }
        }

        return tabIndex;
    }

    private int modulo(final int a, final int b) {
        if (a >= 0) {
            return a % b;
        } else {
            return b + a;
        }
    }

}
