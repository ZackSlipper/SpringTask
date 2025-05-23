CREATE TABLE comment (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	movie_id BIGINT NOT NULL,
	user_id BIGINT NOT NULL,
	comment TEXT NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	FOREIGN KEY (movie_id) REFERENCES movie(id)
		ON DELETE CASCADE,
	FOREIGN KEY (user_id) REFERENCES user(id)
		ON DELETE CASCADE
)