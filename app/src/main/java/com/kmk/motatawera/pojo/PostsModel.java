package com.kmk.motatawera.pojo;

import java.sql.Time;

public class PostsModel
{
        private String post_id , post_body , post_image;
        private Time time_post ;

        public PostsModel(String post_id, String post_body, String post_image, Time time_post) {
                this.post_id = post_id;
                this.post_body = post_body;
                this.post_image = post_image;
                this.time_post = time_post;
        }

        public String getPost_id() {
                return post_id;
        }

        public void setPost_id(String post_id) {
                this.post_id = post_id;
        }

        public String getPost_body() {
                return post_body;
        }

        public void setPost_body(String post_body) {
                this.post_body = post_body;
        }

        public String getPost_image() {
                return post_image;
        }

        public void setPost_image(String post_image) {
                this.post_image = post_image;
        }

        public Time getTime_post() {
                return time_post;
        }

        public void setTime_post(Time time_post) {
                this.time_post = time_post;
        }
}
