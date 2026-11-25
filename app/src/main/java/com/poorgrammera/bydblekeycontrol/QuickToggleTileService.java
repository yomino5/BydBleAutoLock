package com.poorgrammera.bydblekeycontrol;

import android.content.Intent;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public class QuickToggleTileService extends TileService {

    private static boolean isServiceEnabled = false;

    @Override
    public void onStartListening() {
        super.onStartListening();
        updateTileState();
    }

    @Override
    public void onClick() {
        super.onClick();
        isServiceEnabled = !isServiceEnabled;

        Intent serviceIntent = new Intent(this, VehicleAccessService.class);
        if (isServiceEnabled) {
            startForegroundService(serviceIntent);
        } else {
            stopService(serviceIntent);
        }

        updateTileState();
    }

    private void updateTileState() {
        Tile tile = getQsTile();
        if (tile == null) return;

        tile.setState(isServiceEnabled ? Tile.STATE_ACTIVE : Tile.STATE_INACTIVE);
        tile.setLabel("오토락");
        tile.setSubtitle(isServiceEnabled ? "실행 중" : "꺼짐");
        tile.updateTile();
    }
}
