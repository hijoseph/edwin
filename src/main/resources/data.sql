
-- 기존 데이터
INSERT INTO article(title, content) VALUES('제목1', '내용1');
INSERT INTO article(title, content) VALUES('제목2', '내용2');
INSERT INTO article(title, content) VALUES('제목3', '내용3');

INSERT INTO article(title, content) VALUES('제목4', '내용4');
INSERT INTO article(title, content) VALUES('제목5', '내용5');
INSERT INTO article(title, content) VALUES('제목6', '내용6');

-- 댓글 추가
INSERT INTO comment(article_id, nickname, body) VALUES(4, '댓글1','댓글내용1');
INSERT INTO comment(article_id, nickname, body) VALUES(4, '댓글2','댓글내용2');
INSERT INTO comment(article_id, nickname, body) VALUES(4, '댓글3','댓글내용3');
INSERT INTO comment(article_id, nickname, body) VALUES(5, '댓글4','댓글내용4');
INSERT INTO comment(article_id, nickname, body) VALUES(5, '댓글5','댓글내용5');
INSERT INTO comment(article_id, nickname, body) VALUES(5, '댓글6','댓글내용6');
INSERT INTO comment(article_id, nickname, body) VALUES(6, '댓글7','댓글내용7');
INSERT INTO comment(article_id, nickname, body) VALUES(6, '댓글8','댓글내용8');
INSERT INTO comment(article_id, nickname, body) VALUES(6, '댓글9','댓글내용9');
