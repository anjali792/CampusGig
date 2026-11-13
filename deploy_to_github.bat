@echo off
title CampusGig 1-Click GitHub Deployer
powershell -NoProfile -ExecutionPolicy Bypass -File "%~dp0push_to_github.ps1"
pause
