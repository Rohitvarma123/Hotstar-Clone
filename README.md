# Hotstar Clone Deployment

This repository contains a React frontend and a Spring Boot backend. The deployment flow is:

1. A push to `main` or `master` starts GitHub Actions.
2. GitHub Actions builds separate frontend and backend Docker images.
3. Both images are pushed to Docker Hub with the commit SHA and `latest` tags.
4. Ansible connects to the Kubernetes controller and applies the manifests.
5. Kubernetes runs the frontend and backend as separate deployments and services.

## Local builds

```powershell
npm ci
npm run build
mvn -B -f backend\pom.xml package
```

Build the images locally:

```powershell
docker build -t hotstar-frontend:local .
docker build -t hotstar-backend:local .\backend
```

## GitHub Actions secrets

Configure these repository secrets before pushing:

- `DOCKERHUB_USERNAME`
- `DOCKERHUB_TOKEN`
- `DEPLOY_HOST`: address of the Kubernetes controller
- `DEPLOY_USER`: SSH user, normally `ubuntu`
- `DEPLOY_SSH_KEY`: private SSH key for the deployment host

The workflow is [ci-cd.yml](.github/workflows/ci-cd.yml). The canonical Kubernetes manifests are in `backend/k8s/`, and the Ansible files are in `ansible/`.

## Manual deployment

```powershell
kubectl apply -f backend\k8s
kubectl rollout status deployment/hotstar-frontend
kubectl rollout status deployment/hotstar-backend
```

Terraform in `EKS_TERRAFORM/` provisions the EKS cluster. Review the AWS account, region, and remote state bucket before running it.
