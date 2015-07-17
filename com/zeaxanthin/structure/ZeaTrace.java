package com.zeaxanthin.structure;

import java.io.Serializable;

import com.mxgraph.io.mxCodecRegistry;
import com.mxgraph.io.mxObjectCodec;

public class ZeaTrace implements Serializable
{
    private static boolean registered = false;

    public ZeaTrace(){}
    
    public static void registerZeaTraceElements()
    {
        if(registered)
            return;
            
        mxCodecRegistry.addPackage("org.zeatrace");
        mxCodecRegistry.register(new mxObjectCodec(new com.zeaxanthin.structure.Cob()));
        mxCodecRegistry.register(new mxObjectCodec(new com.zeaxanthin.structure.Plant()));
        mxCodecRegistry.register(new mxObjectCodec(new com.zeaxanthin.structure.Seed()));
        mxCodecRegistry.register(new mxObjectCodec(new com.zeaxanthin.structure.Seeds()));
        mxCodecRegistry.register(new mxObjectCodec(new com.zeaxanthin.structure.Transposon()));
        
        registered = true;
        
        return;
    }
}