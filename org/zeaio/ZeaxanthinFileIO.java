package org.zeaio;

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
//our libs
import org.zeatrace.ZeaNode;

public class ZeaxanthinFileIO extends PrintWriter
{
    private String fileName = "Unknown";
    private ArrayList<ZeaNode> allNodes;

    
    public FilePrintWriter(String fileName, String charset)
           throws FileNotFoundException, UnsupportedEncodingException
    {
        super(fileName, charset);
        this.fileName = fileName;
        this.allNodes = new ArrayList<ZeaNode>();
    }
    public FilePrintWriter(String fileName)
           throws FileNotFoundException
    {
        super(fileName);
        this.fileName = fileName;
        this.allNodes = new ArrayList<ZeaNode>();
    }
    
    public void addNodes(ArrayList<ZeaNode> additionalNodes)
    {
        for(ZeaNode tmp : additionalNodes) {
            this.allNodes.add(tmp);
        }
        return;
    }
    public void addNode(ZeaNode additionalNode)
    {
        this.allNodes.add(additionalNode);
        return;
    }
}