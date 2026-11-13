# =========================================================================
# CampusGig 1-Click GitHub Deployer Script
# =========================================================================

$env:Path = "C:\Users\ANJALI SINGH SENGAR\.git-tool\cmd;C:\Users\ANJALI SINGH SENGAR\.gh-tool;$env:Path"

Clear-Host
Write-Host "============================================================" -ForegroundColor Cyan
Write-Host " 🎓 CampusGig - 1-Click Automated GitHub Deployment" -ForegroundColor Yellow
Write-Host "============================================================" -ForegroundColor Cyan
Write-Host " Author: Anjali Singh Sengar <sengara585@gmail.com>" -ForegroundColor Gray
Write-Host " Branch: main" -ForegroundColor Gray
Write-Host " Commits Ready: 2 production commits (62 files)" -ForegroundColor Gray
Write-Host "------------------------------------------------------------`n" -ForegroundColor DarkGray

# Check gh authentication
$authCheck = & gh auth status 2>&1
$isLoggedIn = ($LASTEXITCODE -eq 0)

if (-not $isLoggedIn) {
    Write-Host "🔑 Step 1/2: GitHub Login Required (One-time only)" -ForegroundColor Yellow
    Write-Host "Browser window khulne par 'Authorize' par click karein...`n" -ForegroundColor White
    & gh auth login --web -h github.com -p https
}

Write-Host "`n🚀 Step 2/2: Creating & Pushing to GitHub Repository 'CampusGig'..." -ForegroundColor Green

$remoteUrl = git remote get-url origin 2>$null
if (-not $remoteUrl) {
    & gh repo create CampusGig --public --source=. --remote=origin --push
} else {
    git push -u origin main
}

if ($LASTEXITCODE -eq 0) {
    Write-Host "`n============================================================" -ForegroundColor Cyan
    Write-Host " 🎉 CONGRATULATIONS! CampusGig IS LIVE ON GITHUB!" -ForegroundColor Green
    Write-Host " Aapki repository link ready hai!" -ForegroundColor Yellow
    Write-Host "============================================================" -ForegroundColor Cyan
} else {
    Write-Host "`n⚠️ Agar koi issue aaye to aap apna GitHub repo link directly add kar sakte hain:" -ForegroundColor Yellow
    Write-Host "git remote add origin https://github.com/<your-username>/CampusGig.git" -ForegroundColor White
    Write-Host "git push -u origin main" -ForegroundColor White
}
