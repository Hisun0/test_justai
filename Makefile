docker-run:
	docker run -it -p 8080:8080 divaan/test_justai

run-ngrok:
	ngrok start --config ngrok.yml test