from django.contrib import admin
from .models import Calendar # 각 테이블에 대한 관리자 등록

# Register your models here.
admin.site.register(Calendar)