# Lending

How to deploy:

cd ../trandata

docker build -t trandata-backend:v1 .

cd ../lending

docker build -t lending-backend:v1 .

docker compose down && docker compose up --build -d && docker logs -f


