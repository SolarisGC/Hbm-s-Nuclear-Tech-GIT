package com.hbm.compat.industrialwires;

import com.hbm.blocks.machine.rbmk.RBMKConsole;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKConsole;
import blusunrize.immersiveengineering.api.energy.wires.ImmersiveNetHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class RBMKConsoleIW extends RBMKConsole {
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        if(meta >= offset)
            return new TileEntityRBMKConsoleIW();
        return null;
    }
}

class TileEntityRBMKConsoleIW extends TileEntityRBMKConsole {
    @Override
    public void updateEntity() {
        super.updateEntity();
        if(!worldObj.isRemote) {
            // Aqui você pode adicionar o código para interagir com o Industrial Wires
            // Por exemplo, você pode querer verificar se há energia suficiente na rede
            ImmersiveNetHandler handler = ImmersiveNetHandler.INSTANCE;
            double energy = handler.getEnergyStored(worldObj, pos);
            if(energy < someThreshold) {
                // Se a energia for menor que algum limite, execute o scram
                this.scram();
            }
        }
    }
}
