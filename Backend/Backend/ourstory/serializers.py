from rest_framework import serializers
from .models import Calendar

# get, post, put, delete 가능
class CalendarSerializer(serializers.ModelSerializer):
    class Meta:
        model = Calendar
        fields = '__all__'
