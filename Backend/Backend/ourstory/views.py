from django.shortcuts import render

# 모델 파일에 대한 테이블은 추후에 추가할 예정
from .serializers import CalendarSerializer
from .models import Calendar
from rest_framework import viewsets

# Create your views here.

# ViewSet : Post, Get, Put, Delete 기본기능 내장
class CalendarViewSet(viewsets.ModelViewSet):
    queryset = Calendar.objects.all()  
    serializer_class = CalendarSerializer  