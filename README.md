CH-1  Introduction
1.1 Problems
I.	Can’t access mail data with offline mode.
II.	Can’t use both protocol IMAP4 and POP3 in same application.
III.	Can’t configure more than one account in same application.
IV.	Can’t filters mails with popular mail flags.
V.	Can’t set mail priorities.
VI.	Application is platform dependent, not able to run on UNIX, Linux, Mac.
VII.	Application can’t interpret HTML encoded mails.
VIII.	Insecure credential communication.
IX.	Takes more size in hard drive.
X.	Can’t switch account while composing new mails.
Solutions 
1.2 An Introduction to SkyMail

SkyMail is Enterprise level multiplatform Mail User Agent application for mailing services, the role of SkyMail is not only to transferring, delivering mails like other MTA (Mail Transfer Agent) but duty of SkyMail is read and write and sending electronic mails.
SkyMail handles actual delivery of electronic mail, SkyMail is desktop based application like MS Outlook, Mozzila thunderbird, Eudora, pine, Columba etc., SkyMail Supports multiple incoming and outgoing protocols like(IMAP4,POP3,SMTP) with SSL(Secure Socket Layer),it’s also support multiple folder and flags so end-users can easily filters mails.
SkyMail is also capable to handle all Popular ISP’s mails account like google gmail, yahoo ymail, Microsoft hotmail-live, or any specific domain emails account.
	

1.3 An Alternative to SkyMail

In market other Mail User Agent type applications are available, but following features are not available in gmail, hotmail, live, yahoomail , rediffmail etc. so SkyMail  is more better than all of them.  
•	Same application runs on any platform like UNIX, Linux, Windows, Mac.
•	SkyMail is having ability for end-users to access mail data while offline (Without Internet Connection).
•	End-users can switch account while composing new mails and end-users can set any of them as a default account so while using new composing no need to select, default account is set as Identity of mail sender.
•	SkyMail allows you to configure more than one account with different protocols or SSL.
•	SkyMail gives end-users to set mail priority to send mail.
•	SkyMail stores end-users credential in end-users’s PC, so it’s more than secure to store credential on anywhere else rather than end-users Machine.
•	SkyMail is only 3MB application so it can’t take more space in end-users hard-drive.













CH-2 Requirements
2.1 Software Requirement 
Content 	Description
OS	Windows XP or above
Database	MS SQL SERVER 2005
JDK	JDK 1.6 or above

2.2 Hardware Requirement 
Content	Description
Used	HDD	500 GB
	RAM	4 GB
Required	HDD	20 GB
	RAM	128 MB

2.3 Tools 
Content 	Description
IDE	NetBeans IDE 7.3.1
Used API	JDK 1.7
	Java Mail API(mail.jar)
	MySql java Connector API 
	Absolute Layout API



CH-3 Features of SkyMail.

•	SkyMail is an Enterprise level Mail User Agent(MUA) application like Outlook, Mozzila Thunderbird. 
•	With help of SkyMail you can configure more than one Email Accounts, it Also Supports POP3 and IMAP4, SMTP Internet protocols, End-users can Configure account preferences with an application, user can also configure SSL with account.
•	 SkyMail having facilities to display following Built-in Folders of mail provider
o	INBOX
o	SENT MAIL
o	DRAFTS
o	TRASH
For more(Appendix-C)
•	SkyMail also having facilities to switch mail flag options like
o	RECENT
o	SEEN
o	DELETED
o	ANSWERED
o	DRAFTED
o	ALL
For more(Appendix-D)
•	So user can filter their mail list very easily.
•	SkyMail having facilities to search mail by subject and sender in account.
•	Skymail also support html mails, it automatically compiles html code and display output to end-users.
•	With help of SkyMail user can send and receive mails, while sending mail user can easily switch their multiple user accounts in just a moment.
•	With help of SkyMail user can also sent multiple attachments and also set mail priorities.
•	SkyMail is developed in very secure and latest technology JAVA-J2EE, and most important feature is it is platform independent means it can run on any platform like windows, UNIX, linux and also on Mac.
•	Skymail is also comfortable with hand-held devices like smart-phone,I-phone,S-60 series phones.etc where Java should be supported.
•	Once you received mails with internet connection then in also off-line you can see mails data.
•	Skymail also support attachments sending and receding of any extension types.
•	Very speedy and secure communication with SkyMail.

 
CH-4 Use Cases
	4.1 Procedure to create new account
To create a new account in SkyMail is pretty much easy but requires some technical knowledge, for sending mail SkyMail uses SMTP protocol and receiving emails SkyMail support both POP3 and IMAP4 incoming protocols.
For creating a new account End-User have to follow following steps
Note:- If there is no email account already configured with SkyMail then SkyMail Application automatically pop-ups a new email account creation dialog.
I.	Open a SkyMail Application
II.	Go to Settings->Add new account
III.	SkyMail pop-ups a new dialog box
IV.	Where End-User should have to provide following information
a.	Name of End-User
b.	Email id of End-User that he/she wish to use with SkyMail
c.	Password of email-ID which is provided in step-b
d.	Select Incoming Server into IMAP4 or POP3
e.	Provide incoming server host address (for incoming server Appendix-A)
f.	Provide port no of incoming server (for popular host and port no. Appendix-B)
g.	Provide Outgoing server host address(SMTP host address)
h.	Provide port no of outgoing server (Appendix-B)
i.	Click on OK

By following above steps End-User will have successfully created an account.

 
4.2 Application Preferences
In SkyMail home screen End-User can see how many total mails, unread mails in INBOX, SENT, DRAFT, TRASH are shown bottom-right of SkyMail Screen.
New unread mail is displayed in top of all inbox mail with bold interactions, by clicking any particular mails following thing end-user can see.
I.	Sender of mail.
II.	Receiver of mail.
III.	Subject of mail.
IV.	Full Date and time of mail.
V.	Content of mail.

            If there is any attachment then it automatically pops-up whether End-User wishes to save an attachment or not.

By Default all SkyMail Attachment are stored in D:\SkyMail\
 Directory also End-User can change location while saving an attachment.

			If email contains HTML encoded content then SkyMail automatically interprets and display Output to End-User.

			For compose a new email use Compose Button, in left side of an application there is various folders are located you can switch mails by just clicking on buttons, for new mails arrival you can click on get mails button, End-User can also search mails and filter mails by various popular flags.

			For Reply a mail End-User have to click on Reply Button after selecting a mail, for forward a mail end-user have to click on Forward Button after selecting a mail, for Deleting a mail End-User have to click on Delete Button after selecting a mail.
		NOTE: - By Clicking on Delete button SkyMail Permanently deletes a mail.
4.3 Account Preferences
SkyMail having facility to communicate End-Users multiple accounts which are already configured.
	Go to Settings->Account preferences where End-User can see list of all configured account with types of Incoming server protocol (i.e. either IMAP or POP3) 
	For adding new email accounts click on Add button then follow steps of 4.1 , after selecting any one account from list End-User can Edit,Remove,Set as default account.
	Note:- If there is 1 email account are configured then it’s by system default account.
	Benefit to default account is while you open SkyMail Application automatically data of mail displays, while composing a new mail no need to select your predefine account, automatically default account will selected.
	For editing an account you can change each and every fields of them but you can’t create duplicate email account of same email ID.
	While removing an email account all mails data is permanently removed.

 
4.4 Getting new mails.
		If SkyMail application is currently open and that time if any mails is newly arrived then End-User can get that mails by click on Get Mails button,All newly arrived mails are displayed in INBOX with Bold face.
		 4.5 Compose new mail.
           To Composing a new mail End-User have to click on Compose Button, or go to Message->Compose new mail, SkyMail will pops-up dialog to sent new mail.
	Where End-User can type Receiver address to which he/she wants to send an email, to send email to multiple receiver you can use, (comma-separate) symbol then type another email address of receiver.
	End-User can also send mail to CC(Carbon Copy) and BCC(Black Carbon copy) type receiver also but mail TO receiver type is mandatory, CC Button is used to add CC type receiver and BCC Button used to add BCC type receiver then subject should be typed and then End-User can put content in following options.
I.	Plaint content 
II.	HTML coded content
III.	Only Attachment
IV.	Content with an attachment

                      For Sending an attachment with email End-User can click on Attachment button, then SkyMail pops-up select file to add as attachment, you can add an attachment as you wish but limit size of attachment as per ISP that you are using.
End-User can add any types of file as an attachment, for removing attachment in bottom of compose mail you can find URL of an attachment that you can remove for removing an attachment.


You can set priority of mail by selecting priority, SkyMail Provides you following priorities to send an email.
I.	Highest
II.	High
III.	Normal
IV.	Low
V.	Lowest
By default Normal Priority is set in SkyMail.

4.6  Search mail by subject or from
SkyMail provides Search mail facility to search an emails by subject of mail or from,SkyMail also support search by relevant keywords.
By type a keyword and pressing Enter Key from Keyboard returns result in a particular folder or End-User can click on Search Button gives you results.
For ex. Subject is “Happy Diwali” then write “Happy Diwali” and press Enter Key,if Receiver address is “chetan.khatri@live.com” then write it and press Enter key or click on search Button.
4.7	Reply or Forward  or Delete a mail

SkyMail provides facility to reply mail to sender of an email,By clicking on Reply Button after selecting an email in Home Screen  of SkyMail.
End-User can also forward an email to another email address By clicking on Forward Button after selecting an email in Home Screen of SkyMail.
End-User can also delete and email by clicking on Delete Button after selecting an email in Home Screen of SkyMail.


 CH-5 Data Dictionary and ERD

Table Name: tbaccount
Column Name	Data type	Remarks
acc_id	INT(11)	PK
acc_name	VARCHAR(45)	
acc_mail	VARCHAR(45)	
acc_pwd	VARCHAR(45)	
acc_type	VARCHAR(7)	
acc_inhost	VARCHAR(20)	
acc_outhost	VARCHAR(20)	
Inport	CHAR(5)	
Outport	CHAR(5)	
Status	CHAR(2)	
Table Name: tbinbox
inbox_id	INT(11)	FK(tbaccount.acc_id)
msg_from	VARCHAR(150)	
msg_sub	VARCHAR(150)	
msg_dte	VARCHAR(100)	
msg_size	VARCHAR(45)	
Table Name: tbsent
sent_id	INT(11)	FK(tbaccount.acc_id)
msg_from	VARCHAR(150)	
msg_sub	VARCHAR(150)	
msg_dte	VARCHAR(100)	
msg_size	VARCHAR(45)	
Table Name: tbdrafts
draft_id	INT(11)	FK(tbaccount.acc_id)
msg_from	VARCHAR(150)	
msg_sub	VARCHAR(150)	
msg_dte	VARCHAR(100)	
msg_size	VARCHAR(45)	
Table Name: tbtrash
trash_id	INT(11)	FK(tbaccount.acc_id)
msg_from	VARCHAR(150)	
msg_sub	VARCHAR(150)	
msg_dte	VARCHAR(100)	
msg_size	VARCHAR(45)	





















ERD (Entity Relationship Diagram)

 

 
APPENDICES
APPENDIX-A Incoming and Outgoing Server Properties
Incoming Server 
I) POP3 
POP stands for Post Office Protocol. Currently in version 3, also known as POP3, RFC 1939 defines this protocol. POP is the mechanism most people on the Internet use to get their mail. It defines support for a single mailbox for each user. That is all it does, and that is also the source of most confusion. Much of what people are familiar with when using POP, like the ability to see how many new mail messages they have, are not supported by POP at all.
•	Supports only single mailbox for each user
•	POP doesn’t support ability to see how many new mails arrived.
•	Only INBOX folder is available with POP
•	POP doesn’t support flags.
2) IMAP
IMAP is a more advanced protocol for receiving messages. Defined in RFC 2060, IMAP stands for Internet Message Access Protocol, and is currently in version 4, also known as IMAP4. When using IMAP, your mail server must support the protocol. You can't just change your program to use IMAP instead of POP and expect everything in IMAP to be supported. Assuming your mail server supports IMAP,
Due to the more advanced capabilities, you might think IMAP would be used by everyone. It isn't. It places a much heavier burden on the mail server, requiring the server to receive the new messages, deliver them to users when requested, and maintain them in multiple folders for each user. While this does centralize backups, as users' long-term mail folders get larger and larger, everyone suffers when disk space is exhausted. With POP, saved messages get offloaded from the mail server.  
Outgoing Server 
1) SMTP
The Simple Mail Transfer Protocol (SMTP) is the mechanism for delivery of email. SkyMail will communicate with your company or Internet Service Provider's (ISP's) SMTP server. That SMTP server will relay the message on to the SMTP server of the recipient(s) to eventually be acquired by the user(s) through POP or IMAP. This does not require your SMTP server to be an open relay, as authentication is supported, but it is your responsibility to ensure the SMTP server is configured properly.









APPENDIX-B Host and port no’s of Popular ISP’s
Incoming Server Properties
I) IMAP
For your own domain email account or your company’s domain email account you have to use following host address
mail.domainname.tld
ex. mail.padmaglobal.com
Port no. 143
Note:- Port no given above is a  popular and mostly used  but you should have to check your domain mailing configuration.
List of popular ISP’s host address and port no.
ISP	Host address	Port No.
Google	imap.gmail.com	993
Yahoo	Imap.mail.yahoo.com	993
Microsoft	NOT Supports	--

II) POP3
For your own domain email account or your company’s domain email account you have to use following host address
mail.domainname.tld
ex. mail.padmaglobal.com
Port no. 995



List of popular ISP’s host address and port no.
ISP	Host address	Port No.
Google	pop.gmail.com	993
Yahoo	pop.mail.yahoo.com	995(Only for premium customer)
Microsoft	pop3.live.com	995

Outgoing Server Properties
I) SMTP
For your own domain email account or your company’s domain email account you have to use following host address
mail.domainname.tld
ex. mail.padmaglobal.com
Port no. 25
List of popular ISP’s host address and port no.
ISP	Host address	Port No.
Google	smtp.gmail.com	465(SSL)/587(TLS)
Yahoo	smtp.mail.yahoo.com	465(SSL)/587(TLS)/25
Microsoft	smtp.live.com	25(SSL)/587(SSL/TLS)





APPENDIX-C Mail Folders
Most popular ISP and company domain provider support IMAP protocol still its need to check whether in server imap is installed or not, domain mailing services control panel also displays configuration of IMAP.
INBOX
Inbox folder displays all unread and read mails received from others.
SENT
Sent folder displays all mail which is sent using your email address to others.
DRAFTS
Drafts folder displays you all mails which were already saved.
TRASH
Trash folder displays you all mails which are deleted and which are not permanently deleted.

Note: POP3 Protocol only supports INBOX folder.





APPENDIX-D Mail Flags
All
All flags display you all mails of Inbox folder.
Recent
Recent flags display you mails which are recently viewed.
Seen
Seen flags display you mails which were read.
Answered
Answered flags display you mails in which End-User replied.
Deleted
Deleted flags display you mails which were deleted.
Draft
Draft flags display you mails which are saved as draft.










BIBLIOGRAPHY
Java Mail API    22/07/2013
http://www.oracle.com/technetwork/java/javamail/index.html
http://www.oracle.com/technetwork/java/index-138643.html
MySql Download Connector/J     12/08/2013
http://dev.mysql.com/downloads/connector/j/
Download attachments in e-mail messages using JavaMail  5/09/2013
http://www.codejava.net/java-ee/javamail/download-attachments-in-e-mail-messages-using-javamail
Send email with attachment in java       15/09/2013
http://www.codejava.net/java-ee/javamail/send-e-mail-with-attachment-in-java
JavaMail API Fetching Emails                  08/10/2013
http://www.tutorialspoint.com/javamail_api/javamail_api_fetching_emails.htm
Java code sending Mail with Attachment using Java Mail API
17/10/2013
http://metoojava.wordpress.com/2010/03/03/java-code-to-send-mail-with-attachment-using-java-mail-api/
java - Gmail Imap vs Pop3 when using JavaMail API
28/10/2013
http://stackoverflow.com/questions/16897075/gmail-imap-vs-pop3-when-using-javamail-api

MySQL Java tutorial                                         10/11/2013
http://zetcode.com/db/mysqljava/
JDBC - Delete Records Example                  12/11/2013
http://www.tutorialspoint.com/jdbc/jdbc-delete-records.htm
JDBC PreparedStatement interface example   14/11/2013
http://techmyguru.com/JDBC/index.php?section=7
JDBC - Insert Records Example                     16/11/2013
http://www.tutorialspoint.com/jdbc/jdbc-insert-records.htm
JDBC Insert, Update and Delete                16/11/2013
http://come2niks.com/jdbc-insert-update-and-delete-example/
JProgressBar Tutorial Java – YouTube    16/11/2013
http://www.youtube.com/watch?v=KwvObqafDsI
How to Use JProgressBar in netbeans java – YouTube    17/11/2013
http://www.youtube.com/watch?v=l7S41Yu6qf8


