package RFID;

//参数定义
public class rfid_def {
	public static final String  LOADED_RDRDVR_OPT_CATALOG="CATALOG";
	public static final String  LOADED_RDRDVR_OPT_NAME="NAME";
	public static final String  LOADED_RDRDVR_OPT_ID="ID";
	public static final String  LOADED_RDRDVR_OPT_COMMTYPESUPPORTED="COMM_TYPE_SUPPORTED";
	public static final String  RDRDVR_TYPE_READER="Reader";//general reader

	public static final String CONNSTR_NAME_RDTYPE = "RDType" ;
	public static final String CONNSTR_NAME_COMMTYPE="CommType";

	public static final String CONNSTR_NAME_COMMTYPE_COM="COM";
	public static final String CONNSTR_NAME_COMMTYPE_USB="USB";
	public static final String CONNSTR_NAME_COMMTYPE_NET="NET";

	//HID Param
	public static final String CONNSTR_NAME_HIDADDRMODE="AddrMode";
	public static final String CONNSTR_NAME_HIDSERNUM="SerNum";
	//COM Param
	public static final String CONNSTR_NAME_COMNAME="COMName";
	public static final String CONNSTR_NAME_COMBARUD="BaudRate";
	public static final String CONNSTR_NAME_COMFRAME="Frame";
	public static final String CONNSTR_NAME_BUSADDR="BusAddr";
	//TCP,UDP
	public static final String CONNSTR_NAME_REMOTEIP="RemoteIP";
	public static final String CONNSTR_NAME_REMOTEPORT="RemotePort";
	public static final String CONNSTR_NAME_LOCALIP	="LocalIP";
	
	public static final byte AI_TYPE_NEW = 1;//new antenna inventory  (reset RF power)
	public static final byte AI_TYPE_CONTINUE = 2;//continue antenna inventory ;

	// Move position 
	public static final byte RFID_NO_SEEK=0;//No seeking 
	public static final byte RFID_SEEK_FIRST=1;//Seek first
	public static final byte RFID_SEEK_NEXT=2;//Seek next 
	public static final byte RFID_SEEK_LAST	=3;//Seek last
	
	//Air protocol id
    public static final int RFID_APL_UNKNOWN_ID =  0;
    public static final int RFID_APL_ISO15693_ID = 1;
    public static final int RFID_APL_ISO14443A_ID = 2;
    
    //ISO15693 Tag type id
    public static final int RFID_UNKNOWN_PICC_ID =0 ;
    public static final int RFID_ISO15693_PICC_ICODE_SLI_ID = 1 ;
    public static final int RFID_ISO15693_PICC_TI_HFI_PLUS_ID=2;
    public static final int RFID_ISO15693_PICC_ST_M24LRXX_ID=3;/*ST M24 serial*/
    public static final int RFID_ISO15693_PICC_FUJ_MB89R118C_ID=4;
    public static final int RFID_ISO15693_PICC_ST_M24LR64_ID=5;
    public static final int RFID_ISO15693_PICC_ST_M24LR16E_ID=6;
    public static final int RFID_ISO15693_PICC_ICODE_SLIX_ID=7 ;
    public static final int RFID_ISO15693_PICC_TIHFI_STANDARD_ID= 8 ;
    public static final int RFID_ISO15693_PICC_TIHFI_PRO_ID =9;
    //ISO14443a tag type id 
    public static final int RFID_ISO14443A_PICC_NXP_ULTRALIGHT_ID =1;
    public static final int RFID_ISO14443A_PICC_NXP_MIFARE_S50_ID =2;
    public static final int RFID_ISO14443A_PICC_NXP_MIFARE_S70_ID =3;
}
