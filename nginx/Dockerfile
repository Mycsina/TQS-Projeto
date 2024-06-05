# nginx/Dockerfile

FROM nginx:1.25.5-alpine

RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80
EXPOSE 8081
#EXPOSE 443