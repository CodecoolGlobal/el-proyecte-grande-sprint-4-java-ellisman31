FROM node:14.15.4-alpine
WORKDIR /frontend/forcedepartment-app/
COPY /frontend/forcedepartment-app/package*.json ./
RUN npm install
COPY /frontend/forcedepartment-app .
EXPOSE 3000
CMD ["npm", "start"]
