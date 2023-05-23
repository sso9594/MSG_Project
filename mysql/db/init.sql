CREATE USER 'root'@'%' IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
FLUSH PRIVILEGES;

use msg_db;

CREATE TABLE dummydata{
    post_id INT PRIMARY KEY,
    nickname VARCHAR(10),
    useremail VARCHAR(20),
    updatetime VARCHAR(20),
    photo VARCHAR(100),
    content VARCHAR(1000),
    title VARCHAR(100),
    location VARCHAR(50),
    likenum INT,
    commentnum INT,
    category VARCHAR(50)
    };

INSERT INTO msg_db VALUES (4,'Valentine Small','vestibulum@hotmail.couk','May 20, 2023',
                           'http://ebay.com/site?str=se','Quisque tincidunt pede ac urna. Ut tincidunt vehicula risus. Nulla',
                           'luctus et ultrices posuere cubilia','4874 Diam. St.',
                           45,12,'Jeju')
