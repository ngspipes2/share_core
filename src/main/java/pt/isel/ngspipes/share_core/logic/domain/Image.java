package pt.isel.ngspipes.share_core.logic.domain;

public class Image {

    private String id;
    public String getId(){ return this.id; }
    public void setId(String id){ this.id = id; }

    private byte[] content;
    public byte[] getContent() { return this.content; }
    public void setContent(byte[] content) { this.content = content; }



    public Image() { }

    public Image(String id, byte[] content) {
        this.id = id;
        this.content = content;
    }

}
